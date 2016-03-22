title: rxjava源码解析(一)
date: 2016-03-04 00:23:15
tags: [RxJava,source]
---

# RxJava要点解析

## lift变换操作的原理

因为发现，通过过滤操作符filter，发现工作的线程是主线程。
大致的代码如下。

```java
private void simpleFilter() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("Observable:" + Thread.currentThread());
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                System.out.println("filter:" + Thread.currentThread());
                if (integer > 2) {
                    return true;
                } else {
                    return false;
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Action1:" + Thread.currentThread());
                textViewMain.setText(integer.toString());
            }
        });

    }
```

运行以上代码，我们会发现，Observable是运行在IO线程，filter和action是在一个线程。晚上想到，filter返回的也是一个observable，难道需要再次指定它运行的线程？试了一下，发现真的是这样。

```java
private void simpleFilter() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("Observable:" + Thread.currentThread());
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                System.out.println("filter:" + Thread.currentThread());
                if (integer > 2) {
                    return true;
                } else {
                    return false;
                }
            }
        }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Action1:" + Thread.currentThread());
                textViewMain.setText(integer.toString());
            }
        });

    }
```

这个observable充当了subscriber的角色了？那明确定义的subscriber又咋回事呢？不禁想看看这里面的实现原理。

看下lift源码,直接拷贝的源码，未做删减。
```java
public final <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
        return new Observable<R>(new OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> o) {
                try {
                    Subscriber<? super T> st = hook.onLift(operator).call(o);
                    try {
                        // new Subscriber created and being subscribed with so 'onStart' it
                        st.onStart();
                        onSubscribe.call(st);
                    } catch (Throwable e) {
                        // localized capture of errors rather than it skipping all operators
                        // and ending up in the try/catch of the subscribe method which then
                        // prevents onErrorResumeNext and other similar approaches to error handling
                        Exceptions.throwIfFatal(e);
                        st.onError(e);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    // if the lift function failed all we can do is pass the error to the final Subscriber
                    // as we don't have the operator available to us
                    o.onError(e);
                }
            }
        });
    }
 ```

 为了弄懂，而且变量不多，我们一个变量一个变量的看。

 - lift内部返回的是一个新建的observable，此时产生了一个新的OnSubscribe，此时的OnSubscribe的call方法内传入的Subscriber
   变量o就是我们写在代码中的订阅者。

 - hook.onLift(operator).call(o);新建了一个Subscriber变量st，这个变量用的是我们传入的Subscriber变量o。
   而且还是用的operator创建的，我们先不管如何实现的，待会儿我们看看这个是怎么实现的。

 - onSubscribe.call(st);这个onSubscribe是个final类型，因为目前我们还是处于方法内，所以这个onSubscribe还是源observable
   的onSubscribe对象(比如我们自己写的发射时机等那段代码),这个时候onSubscribe会调用它的call方法，传入的是我们新建的Subscriber变量st。
   接下来，新的Subscriber变量st会接收到源observable发送来的数据。我们可以自然得想到，这个新的st肯定会经过operator对象中的一些
   定义的方法对数据操作后，又发送到了我们传入的Subscriber变量o，实现整体的连接。

   其实说白了，我们其实是在中间创建了一个代理。hook的意思不就是钩子嘛。

   接下来来看刚刚未能解决的疑问，hook是怎么工作的。

  ```java
  public <T, R> Operator<? extends R, ? super T> onLift(final Operator<? extends R, ? super T> lift) {
         return lift;
     }
  ```

  我们看到，并未做任何变化，直接将operator变换直接返回了。


  接着我们继续看，以filter为例。
  ```java
  public final Observable<T> filter(Func1<? super T, Boolean> predicate) {
          return lift(new OperatorFilter<T>(predicate));
      }
  ```

 传入的是一个OperatorFilter对象。

 ```java
 public final class OperatorFilter<T> implements Operator<T, T> {

     private final Func1<? super T, Boolean> predicate;

     public OperatorFilter(Func1<? super T, Boolean> predicate) {
         this.predicate = predicate;
     }

     @Override
     public Subscriber<? super T> call(final Subscriber<? super T> child) {
         return new Subscriber<T>(child) {

             @Override
             public void onCompleted() {
                 child.onCompleted();
             }

             @Override
             public void onError(Throwable e) {
                 child.onError(e);
             }

             @Override
             public void onNext(T t) {
                 try {
                     if (predicate.call(t)) {
                         child.onNext(t);
                     } else {
                         // TODO consider a more complicated version that batches these
                         request(1);
                     }
                 } catch (Throwable e) {
                     Exceptions.throwOrReport(e, child, t);
                 }
             }

         };
     }

 }
 ```

 变量predicate就是我们自己定义的过滤规则，在上面的代码中我们已经看到了，call传入的child就是我们上面分析，我们自己定义的变量o。

 这下基本清晰了，每次源observable发射的数据都被OperatorFilter内新的subscriber给接收了，然后根据传入到OperatorFilter我们自己定义的过滤规则进行判断，通过的，就给child发射过去，这样实现了过滤的作用，实现了新的subscriber将数据传送到了我们自定义的subscriber。

### 待解决疑问：
 -  虽然变换的原理弄明白了，还是未找到线程切换的原理。


## Scheduler 线程切换的原理
注意：以下的版本是rxjava1.1.1(上下两部分的总结时间不一样，也不去考证是哪个版本了)
上面说到了变换的时候，用到的线程的切换的问题，那到底是怎么切换的线程呢？
说到线程切换，必须说到两个操作。
- subscribeOn：指定observable调用obsubsriber发射数据所在的线程。
- observeOn： 指定订阅者进行订阅处理所在的线程。

### subscribeOn分析

```java
public final Observable<T> subscribeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
        }
        return create(new OperatorSubscribeOn<T>(this, scheduler));
}
```
以上是subscribeOn的源码，传入了指定的发送数据所在的线程Scheduler对象。判断当前的observable是否是一个ScalarSynchronousObservable，这个ScalarSynchronousObservable对象就是直接发射传入数据的对象，就是我们平时使用just所产生的对象。

-  如果是，则调用将scheduler传入scalarScheduleOn方法，创建一个新的Observable作为返回值。
```java
public Observable<T> scalarScheduleOn(final Scheduler scheduler) {
        final Func1<Action0, Subscription> onSchedule;
        if (scheduler instanceof EventLoopsScheduler) {
            onSchedule = COMPUTATION_ONSCHEDULE;
        } else {
            onSchedule = new Func1<Action0, Subscription>() {
                @Override
                public Subscription call(final Action0 a) {
                    final Scheduler.Worker w = scheduler.createWorker();
                    w.schedule(new Action0() {
                        @Override
                        public void call() {
                            try {
                                a.call();
                            } finally {
                                w.unsubscribe();
                            }
                        }
                    });
                    return w;
                }
            };
        }
        return create(new ScalarAsyncOnSubscribe<T>(t, onSchedule));
    }
```
以上是scalarScheduleOn的源码，可以看到内部是通过Func1函数进行转换的，通过传入的scheduler创建指定的线程，在指定的线程上调用Func1中传入进来的Action0。
那么Action0代表的又是什么呢？我们看到onSchedule又作为参数去构造ScalarAsyncOnSubscribe了。
```java
/**
     * The OnSubscribe implementation that creates the ScalarAsyncProducer for each
     * incoming subscriber.
     *
     * @param <T> the value type
     */
    static final class ScalarAsyncOnSubscribe<T> implements OnSubscribe<T> {
        final T value;
        final Func1<Action0, Subscription> onSchedule;

        ScalarAsyncOnSubscribe(T value, Func1<Action0, Subscription> onSchedule) {
            this.value = value;
            this.onSchedule = onSchedule;
        }

        @Override
        public void call(Subscriber<? super T> s) {
            s.setProducer(new ScalarAsyncProducer<T>(s, value, onSchedule));
        }
    }
```
根据代码注释，知道是为每个传入进来的subscriber创建一个ScalarAsyncProducer。看到这里的call方法，我们可以知道，这个call方法就是被Observable在create中被调用的call方法。在call方法里调用了Subscriber的setProducer方法，给它设置了一个ScalarAsyncProducer对象，这里的Subscriber对象s就是我们自定义的订阅者对象，接下来就看看ScalarAsyncProducer的源码。

```java
/**
     * Represents a producer which schedules the emission of a scalar value on
     * the first positive request via the given scheduler callback.
     *
     * @param <T> the value type
     */
    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0 {
        /** */
        private static final long serialVersionUID = -2466317989629281651L;
        final Subscriber<? super T> actual;
        final T value;
        final Func1<Action0, Subscription> onSchedule;

        public ScalarAsyncProducer(Subscriber<? super T> actual, T value, Func1<Action0, Subscription> onSchedule) {
            this.actual = actual;
            this.value = value;
            this.onSchedule = onSchedule;
        }

        @Override
        public void request(long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            if (n != 0 && compareAndSet(false, true)) {
                actual.add(onSchedule.call(this));
            }
        }

        @Override
        public void call() {
            Subscriber<? super T> a = actual;
            if (a.isUnsubscribed()) {
                return;
            }
            T v = value;
            try {
                a.onNext(v);
            } catch (Throwable e) {
                Exceptions.throwOrReport(e, a, v);
                return;
            }
            if (a.isUnsubscribed()) {
                return;
            }
            a.onCompleted();
        }

        @Override
        public String toString() {
            return "ScalarAsyncProducer[" + value + ", " + get() + "]";
        }
    }
```
注释上说，这个类是用来代表一个生产者，这个生产者通过给定的线程回调，在第一个激活的请求上发射数据。在具体看代码之前，我们可以根据注释大致猜到这个是充当了发射数据的生产者，并且要是能实现线程切换，应该是在作为构造参数传递进来的onSchedule上进行调用和回调的。看下里面的源码，
在request方法中，
这里的actual就是刚刚传入的自定义订阅者对象s,我们看到call方法中就是直接调用了actual的onNext方法，将数据传递到订阅者。
再看重点，request方法，有个参数n代表一次性请求的数据的数量，actual.add(onSchedule.call(this))；这个命令我们看到有调用onSchedule的call方法，那么经过这么长时间下来，这个call方法又是啥玩意？这个onSchedule就是我们在scalarScheduleOn中定义的Func1，这个也就是说在这里调用了Func1的call方法，传递进去的按理说是Action0对象，我们注意到ScalarAsyncProducer已经实现了Action0接口。也就是说这里调用了ScalarAsyncProducer(生产者)的call方法。当前ScalarAsyncProducer的call方法就是直接调用自定义订阅者的onNext发射数据。我们在上面说了Func1的call方法会在一个新建的线程中调用call方法传递进来的action0对象(就是此处实现Action0接口的ScalarAsyncProducer)的call方法。到目前为止，关于ScalarSynchronousObservable对象的线程切换的原理就分析结束了。

-  接下来看下，如果不是ScalarSynchronousObservable对象调用subscribeOn方法又会是什么逻辑呢？

再贴下subscribeOn的源码。
```java
public final Observable<T> subscribeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
        }
        return create(new OperatorSubscribeOn<T>(this, scheduler));
    }
```

根据当前的observable对象和传递进来的scheduler创建了一个OperatorSubscribeOn对象，然后用这个OperatorSubscribeOn对象创建了一个新的Observable。

```java
/**
 * Subscribes Observers on the specified {@code Scheduler}.
 * <p>
 * <img width="640" src="https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/subscribeOn.png" alt="">
 *
 * @param <T> the value type of the actual source
 */
public final class OperatorSubscribeOn<T> implements OnSubscribe<T> {

    final Scheduler scheduler;
    final Observable<T> source;

    public OperatorSubscribeOn(Observable<T> source, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = source;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final Worker inner = scheduler.createWorker();
        subscriber.add(inner);

        inner.schedule(new Action0() {
            @Override
            public void call() {
                final Thread t = Thread.currentThread();

                Subscriber<T> s = new Subscriber<T>(subscriber) {
                    @Override
                    public void onNext(T t) {
                        subscriber.onNext(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            subscriber.onError(e);
                        } finally {
                            inner.unsubscribe();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        try {
                            subscriber.onCompleted();
                        } finally {
                            inner.unsubscribe();
                        }
                    }

                    @Override
                    public void setProducer(final Producer p) {
                        subscriber.setProducer(new Producer() {
                            @Override
                            public void request(final long n) {
                                if (t == Thread.currentThread()) {
                                    p.request(n);
                                } else {
                                    inner.schedule(new Action0() {
                                        @Override
                                        public void call() {
                                            p.request(n);
                                        }
                                    });
                                }
                            }
                        });
                    }
                };

                source.unsafeSubscribe(s);
            }
        });
    }
}
```
注释直接表明，这个类是用来在指定的线程上指定subscriber。最主要的方法是call方法，在这个方法里，创建了一个Worker对象，Worker对象调用schedule方法传入一个Action0,
在这里Action0的call方法被调用的时候，首先记录当前调用Action0的call方法的线程。在内部用传入的原始subscriber创建一个新的Subscriber对象s,在这个s的内部又进行原始subscriber的onNext方法调用发射数据，又覆写了新的subscriber对象的setProducer方法，在覆写的时候对原始的subscriber对象设置一个新的Producer(生产者)，在这个新的生产者的request方法中，会判断当前的线程会否就是scheduler指定的线程(刚刚在Action0的call方法中记录了)，如果是，则立即执行；否则将交给scheduler对象指定的Worker对象重新安排这个任务，直到线程一致为止才执行。最后让执行subscribeOn产生的新的observable去订阅这个新产生的subscriber即可。

####  subscribeOn总结：

线程的切换是由Scheduler对象中的Worker对象负责安排这些任务，不同类型的Scheduler创建出对应的Worker对象，在Worker对象内部会在相应的线程上创建新的Subscriber，通过subscriber，新的subscriber再设置Producer，通过Producer代理了实现发射线程的切换。

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

上面说到了变换的时候，用到的线程的切换的问题，那到底是怎么切换的线程呢？
说到线程切换，必须说到两个操作。
- subscribeOn：指定observable调用obsubsriber发射数据所在的线程。
- observeOn： 指定订阅者进行订阅处理所在的线程。

```java
public final Observable<T> subscribeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable<T>)this).scalarScheduleOn(scheduler);
        }
        return nest().lift(new OperatorSubscribeOn<T>(scheduler));
    }
```
以上是subscribeOn的源码。传入了指定的发送数据所在的线程Scheduler对象，判断当前的observable是否是一个需要同步的数据流，如果是怎么根据同步的方式进行指定，如果不是，就将当前的这个observable进行一次lift变换。
看下nest方法是什么？
```java
public final Observable<Observable<T>> nest() {
        return just(this);
    }
```
创建了一个新的observable对象，这个新的observable对象将会直接发射我们当前的这个observable。

再看下just方法的内容。

```java
public final static <T> Observable<T> just(final T value) {
        return ScalarSynchronousObservable.create(value);
    }
```

just方法返回的就是一个在subscribeOn中会加以判断的ScalarSynchronousObservable类型。也就是说中会判断是否是ScalarSynchronousObservable类型，不是就根据当前的创造一个ScalarSynchronousObservable类型，再进行lift变换。
我们先不管源observable本来就是ScalarSynchronousObservable类型的情况，待会儿再看，我们先看看大多数需要变换的这个逻辑。

通过nest方法返回一个新的ScalarSynchronousObservable类型的observable，接着进行lift操作，上面的分析指出，lift内部是又创建了一个observable和一个subscriber。(其实到这里，我们已经出现了三个observable)。上面有讲到过，内部实现的时候，是现将发射的数据发送到lift中新建的subscriber，而后这个新的subscriber接收到数据的时候，会先经由operator进行操作，再继续发射给自主设定的subscriber。
我们看到subscribeOn中也是新建了一个操作符 OperatorSubscribeOn<T>(scheduler)，我们猜测大致的原理应该一样，内部应该也是新建的subscriber，新的subscriber也会按照这个新的操作符OperatorSubscribeOn去操作数据，再发射给自主设定的subscriber。
我们看下源码。
```java
public class OperatorSubscribeOn<T> implements Operator<T, Observable<T>> {

    private final Scheduler scheduler;

    public OperatorSubscribeOn(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Subscriber<? super Observable<T>> call(final Subscriber<? super T> subscriber) {
        final Worker inner = scheduler.createWorker();
        subscriber.add(inner);
        return new Subscriber<Observable<T>>(subscriber) {

            @Override
            public void onCompleted() {
                // ignore because this is a nested Observable and we expect only 1 Observable<T> emitted to onNext
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onNext(final Observable<T> o) {
                inner.schedule(new Action0() {

                    @Override
                    public void call() {
                        final Thread t = Thread.currentThread();
                        o.unsafeSubscribe(new Subscriber<T>(subscriber) {

                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(T t) {
                                subscriber.onNext(t);
                            }

                            @Override
                            public void setProducer(final Producer producer) {
                                subscriber.setProducer(new Producer() {

                                    @Override
                                    public void request(final long n) {
                                        if (Thread.currentThread() == t) {
                                            // don't schedule if we're already on the thread (primarily for first setProducer call)
                                            // see unit test 'testSetProducerSynchronousRequest' for more context on this
                                            producer.request(n);
                                        } else {
                                            inner.schedule(new Action0() {

                                                @Override
                                                public void call() {
                                                    producer.request(n);
                                                }
                                            });
                                        }
                                    }

                                });
                            }

                        });
                    }
                });
            }

        };
    }
}
```

我们看到源码和上面的filter指定的差不多。构造的时候会传入一个Scheduler对象，我们直接看里面的call方法，call方法的参数，subscriber就是我们后面自主设定的subscriber，这里通过scheduler.createWorker()，创建了一个worker对象，将这个worker设定给了作为参数传进来的subscriber，而后再用这个时候的subscriber又新建了一个subscriber，这个是到现在为止出现的第二个subscriber，我们看下这个subscriber会怎么运作，当有消息发送来的时候，新建的worker对象就会调用schedule方法，传入一个action0对象，在这个对象里我们看到了。
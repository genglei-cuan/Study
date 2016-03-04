title: rxjava源码解析(一)
date: 2016-03-04 00:23:15
tags: [RxJava,source]
---

# RxJava要点解析

## 变换操作的原理

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

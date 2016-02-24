# RxJava篇

## Why技术产生的背景

在编程中，经常需要切换线程，为了能对结果进行相应处理，经常需要进行回调，随着业务需求的增加，嵌套的回调也会随之增加，不仅增加了代码量，也增加了逻辑的复杂性，增加了理解和维护的难度。
所以就需要一种
- 既能方便切换线程
- 又能即是相应变化
- 又可以简化代码的逻辑，方便维护，
- 还不需要回调。

## What ReactiveX是什么
Reactive Extensions,简称RX，原来只是微软开发的一个LINQ的一个扩展。

微软给的定义是，Rx是一个函数库，让开发者可以利用**可观察序列**和**LINQ风格查询操作符**来编写**异步**和基于**事件**的程序，使用Rx，开发者可以用Observables表示异步数据流，用LINQ操作符查询异步数据流，用Schedulers参数化异步数据流的并发处理，Rx可以这样定义：**Rx = Observables + LINQ + Schedulers。**

ReactiveX.io给的定义是，Rx是一个使用可观察数据流进行异步编程的**编程接口**，ReactiveX结合了观察者模式、迭代器模式和函数式编程的精华。

看完微软给的定义已经很详细了，开源组织给的更加精简，里面提到了数据流还有事件，我们来自己看看怎么理解。

这里得提到响应式编程的概念，其中有两个关键点，
- 事件，事件可以被观察，等待，过滤，响应，也可以触发其他的事件，事件通过数据流的形式对外呈现。
- 数据流，数据流就像一条河：它可以**被观测，被过滤，被操作，或者与另外一条流合并为一条新的流来给新的消费者消费**。

所以，响应式编程就是一种基于异步**数据流**概念的编程模式。其实EventBus还有其他的点击事件一样，本质上就是异步的数据流，我们可以为任何的事件创建数据流。比如我们可以为登录操作创建数据流，然后监听这个数据流，进行登录验证这样的响应操作。

主要特点有：
-	易于并发从而更好的利用服务器的能力。
-	易于有条件的异步执行。
-	一种更好的方式来避免回调地狱。
-	一种响应式方法。

## RxJava与传统的Java的不同
在Rx中，开发者用Observables模拟可被观察的异步数据流，从纯Java的观点看，RxJava的Observable类源自于经典的 Gang Of Four 的观察者模式。


### 与传统观察者的不同

它添加了三个缺少的功能：
- 生产者在没有更多数据可用时能够发出信号通知：onCompleted()事件。
- 生产者在发生错误时能够发出信号通知：onError()事件。
-	RxJava Observables 能够组合而不是嵌套，从而避免开发者陷入回调地狱。


### 与传统的Iterable的不同

Observables和IterablesAPI 是很相似的：我们在Iterable可以执行的许多操作也都同样可以在Observables上执行。当然，由于Observables流的本质，没有如Iterable.remove()这样相应的方法,因为数据可能已经发射出去了，remove也没有任何意义。

使用Iterable时，消费者从生产者那里以同步的方式得到值，在这些值得到之前线程处于阻塞状态。相反，使用Observable时，生产者以异步的方式把值push给观察者，无论何时，这些值都是可用的。这种方法之所以更灵活是因为即便值是同步或异步方式到达，消费者在这两种场景都可以根据自己的需要来处理。


| Pattern        | 一个返回值   |  多个返回值  |
| --------       | -----:  | :----:  |
| Synchronous    | T getData()| Iterable<T>  |
| Asynchronous   | Future<T> getData()  |  Observable<T> getData()   |

Observable的生命周期包含了三种可能的易于与Iterable生命周期事件相比较的事件，下表展示了如何将Observable async/push 与 Iterable sync/pull相关联起来。

| Event       |  Iterable(pull)   |   Observable(push)  |
| --------    | -----:  | :----:  |
| 检索数据    |  T next()          |   onNext(T)         |
| 发现错误    |  throws Exception  |   onError(Throwable)|
| 完成        | !hasNext()        |    onCompleted()     |



所以，由以上这些新增的特点，开发者只要简单的去请求，当请求完成的时候，会得到一个通知。开发者需要对可能发生的每个事件提供一个清晰的响应链。

### 举个例子：
用户提交完用户名和密码，我们可以用observable模拟这个登录的数据流，而后我们需要对可能发生的情况进行定义；
- 用户名密码正确，登录成功，转到登录成功界面。
- 用户名和密码匹配不成功，登录失败，给用户个提示。

这样，我们不需要等待结果，等到有结果的时候，会有通知，这个过程是异步的。这中间可以做很多其他的事情，保存到缓存，显示进度条等等。


## 概念

### Observable
当我们异步执行一些复杂的事情，Java提供了传统的类，例如Thread、Future、FutureTask、CompletableFuture来处理这些问题。当复杂度提升，这些方案就会变得麻烦和难以维护。最糟糕的是，它们都不支持链式调用。RxJava Observables可以解决这些问题。它可以作用于单个结果程序上，也可以作用于序列上。无论何时你想发射单个标量值，或者一连串值，甚至是无穷个数值流，你都可以使用Observable。和传统的观察者模式一样，也有冷热之分。
- 热的observable，只要创建了observable，就开始发射数据了，所以，后续订阅他的observer可能从中间某个位置开始接收数据。
- 冷的observable，等到有订阅的时候才开始发射数据。

## Observer
观察者，订阅observable发射的数据，对其做出相应，对可能出现的情况的定义就在这里。

三个重要的回调方法 (onNext, onCompleted, onError)
通过Subscribe方法可以将观察者连接到Observable，观察者需要实现以下方法的一个子集:
- onNext(T item):Observable调用这个方法发射数据，方法的参数就是Observable发射的数据，这个方法可能会被调用多次，取决于你的实现。
- onError(Exception ex):当Observable遇到错误或者无法返回期望的数据时会调用这个方法，这个调用会终止Observable，后续不会再调用onNext和onCompleted，onError方法的参数是抛出的异常。
- onComplete:正常终止，如果没有遇到错误，Observable在最后一次调用onNext之后调用此方法。

根据Observable协议的定义，onNext可能会被调用**零次或者很多次**，最后会有一次onCompleted或onError调用（不会同时），传递数据给onNext通常被称作发射，onCompleted和onError被称作通知。

## Subscriber
Observers和Subscribers是两个“消费”实体。Subscriber是一个实现了Observer的一个抽象类。相对于基本的observer，提供了手动解开订阅的方法unsubscribe和在 subscribe 刚开始，而事件还未发送之前被调用的方法onStart。其他的使用方式是一样的。
## Subjects
Subject = Observable + Observer。
subject是一个神奇的对象，它可以是一个Observable同时也可以是一个Observer：它作为连接这两个世界的一座桥梁。一个Subject可以订阅一个Observable，就像一个观察者，并且它可以发射新的数据，或者传递它接受到的数据，就像一个Observable。很明显，作为一个Observable，观察者们或者其它Subject都可以订阅它。
一旦Subject订阅了Observable，它将会触发Observable开始发射。如果原始的Observable是“冷”的，这将会对订阅一个“热”的Observable变量产生影响。

# How怎么使用

接下来讨论他的具体使用方法。首先是需要搭建环境，我们就以AS为例。
- 因为就是为了Android开发所学的，在module的gradle中添加RxAndroid的仓库地址,RxAndroid本身是依赖RxJava的，所以会自动下载RxJava的依赖包。
```groovy
compile 'io.reactivex:rxandroid:1.1.0'
```

## 创建Observable

### Create之从头创建
这个操作符传递一个**含有观察者作为参数的函数**的对象，编写这个函数让它的行为表现为一个Observable--恰当的调用观察者的onNext，onError和onCompleted方法。下面是个非常简单的一个例子，先有个直观的大致的认识。

```Java
Observable.OnSubscribe<String> f = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> o) {
                //完全自己决定发射数据给subscriber和通知subscriber的时机以及方式
                o.onNext("发射的数据");
                o.onCompleted();
            }
        };
        Observable observable = Observable.create(f);
        //创建订阅者
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                //正常结束，收到发射的通知
            }

            @Override
            public void onError(Throwable e) {
                //出现了错误的通知
            }

            @Override
            public void onNext(Object o) {
                //收到observable发射的数据
            }
        };
        //订阅，一旦订阅发生，observable将开始发射数据
        observable.subscribe(subscriber);
```

### From
这个操作符需要传入数组或者列表等可以迭代的类型，将会返回一个observable对象，这个observable会迭代列表里的数据，然后将数据一个一个的发射出去。

### Just
这个操作符会返回一个observable，这个observable将传入的对象直接发射出去。这个操作符对于进行旧版本的改造非常有用，对于暂时不想做过多操作的函数，可以直接传入到just操作符中，这样就自动构造出了一个数据流。

### Repeat
这个操作符需要一个整形数字作为参数，代表了重复发射的次数，比如发射“123”三次，就会变成发射"123123123"。

### defer
这个操作符可以延迟observable的创建，当有订阅者的时候才开始创建，这个对于一些不是每次都需要创建的数据流而言，很有用。怎么理解呢，我们简单的看个例子。
```Java
public class MainActivity extends AppCompatActivity {

    private Subscriber subscriber;
    private Observable simpleObservable;

    private String doSomeThing() {
        System.out.println("在执行just的时候，这里需要执行的操作已经执行结束了。。。");
        return "SteveYan";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleObservable = Observable.just(doSomeThing());
        subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                //正常结束，收到发射的通知
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                //出现了错误的通知
            }

            @Override
            public void onNext(Object o) {
                //收到observable发射的数据
                System.out.println("Receive " + o.toString());
            }
        };
        init();
    }

    private void init() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次点击的时候都进行订阅
                simpleObservable.subscribe(subscriber);
            }
        });
    }

}
```
在上面的例子中，每次点击就进行一次订阅，在oncreate方法里，在执行just的时候，doSomeThing已经执行完了，
但是并未发射数据，但是假如使用defer操作符的话，doSomeThing则会等到点击的时候才执行。
修改成的defer操作符的
```Java
public class MainActivity extends AppCompatActivity {

    private Subscriber subscriber;
    private Observable simpleObservable;

    private String doSomeThing() {
        System.out.println("Do Some");
        return "SteveYan";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(doSomeThing());
            }
        });
        subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                //正常结束，收到发射的通知
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                //出现了错误的通知
            }

            @Override
            public void onNext(Object o) {
                //收到observable发射的数据
                System.out.println("Receive " + o.toString());
            }
        };
        init();
    }

    private void init() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次点击的时候都进行订阅
                simpleObservable.subscribe(subscriber);
            }
        });
    }

}
```

### range 从一个指定的数字X开始发射N个数字
range()函数用两个数字作为参数：第一个是起始点，第二个是我们想发射数字的个数。目前未发现在实际项目中的用处。

### interval 需要创建一个轮询程序时非常好用
interval()函数的两个参数：一个指定两次发射的时间间隔，另一个是用到的时间单位。

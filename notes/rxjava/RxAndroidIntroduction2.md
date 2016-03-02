title: RxAndroid入门分享2.md
date: 2016-02-26 22:50:26
tags: [Android,RxJava]
---

# More RxJava 及其在Android上的应用

## 开发环境
- 在module的gradle中添加RxAndroid的相关依赖，如果想体验rx在Android上的更方便的功能。可以添加Jake大神的兼容包RxBinding。
```groovy
    //RXAndroid
    compile 'io.reactivex:rxandroid:1.1.0'
    //RxBinding
    compile 'com.jakewharton.rxbinding:rxbinding:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-support-v4:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-appcompat-v7:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-design:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-recyclerview-v7:0.3.0'
    //compile 'com.jakewharton.rxbinding:rxbinding-leanback-v17:0.3.0'
```

## 以下开始用一个demo来演示

目的地有三个tab，每个tab内的详情用RecyclerView展示，下拉刷新用SwipeRefreshLayout。
布局界面如下
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    <android.support.v4.widget.SwipeRefreshLayout
            android:id="@+id/destinationSwipeRefreshLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

        <android.support.v7.widget.RecyclerView
                android:id="@+id/destinationRecyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:clipToPadding="false">

        </android.support.v7.widget.RecyclerView>

    </android.support.v4.widget.SwipeRefreshLayout>

</FrameLayout>
```
开始RxAndroid的编写，我们开始考虑在目的地页面需要有的步骤
- 网络请求数据(放在IO线程)；
- 填充网络请求返回的数据到页面(UI线程展示)；
RxAndroid是基于响应式的编程，我们考虑将以上的网络请求产生的结果作为一个事件，他产生的数据就可以定义为数据流了。

### Observable&&create
```java
//网络请求，从头开始，自定义创建一个数据流。自主决定数据流的发射时机。
    private Observable<DestinationDataModel> getDestinationDataObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<DestinationDataModel>() {
            @Override
            public void call(Subscriber<? super DestinationDataModel> subscriber) {
                Gson gson = new Gson();
                Request request = new Request.Builder().url(url).build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    DestinationDataModel destinationDataModel = gson.fromJson(response.body().string(), DestinationDataModel.class);
                    if (subscriber.isUnsubscribed()){//判断连接是否断开，避免无谓的操作
                        return;
                    }
                    subscriber.onNext(destinationDataModel);
                    if (!subscriber.isUnsubscribed()){
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
```
这里我们定义了一个方法，这个方法返回一个observable，里面封装了数据流,其实不是很计较的情况下，我们先将observable事件等价(自我的理解，未在官方文档找到合适的解释，如有找到望不吝赐教)，事件对外以数据流的形式展现，这个事件就是在发射数据，然后发射结束就通知，失败了也通知。此处我们用到了操作符create，一种创建操作符，这种操作的扩展性极大，但是我们也需要自己负责发射数据。全程的执行规范都在你的手里。
这里可能会有误区，数据流，不一定是多个，非要像list，数组似的，那种有多个值，像弹珠似的连续发射。像上面的代码，我们只是发射了一个destinationDataModel对象，这个也是数据流，即，你发射出去的就是数据流的子集。


现在，我们有数据流了，就需要在某个适当的地方进行响应。我们思考，需要响应什么？对一个事件可能存在三种情况，事件对应的数据发射失败了(onError)，发射成功了(onCompleted),还有接收到了发射来的数据(onNext)。
有点需要注意，这里的失败，以及成功，这两个回调，只是一个通知而已。


```java
      Subscriber<DestinationDataModel> subscriber = new Subscriber<DestinationDataModel>() {
      @Override
      public void onStart() {
        super.onStart();
      }

      @Override
      public void onCompleted() {
        Toast.makeText(getActivity(), "onCompleted", Toast.LENGTH_SHORT).show();
        destinationSwipeRefreshLayout.setRefreshing(false);
      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
        destinationSwipeRefreshLayout.setRefreshing(false);
      }

      @Override
      public void onNext(DestinationDataModel destinationDataModel) {
        destinationRecyclerView.setAdapter(new DestinationRecyclerAdapter(getActivity(), destinationDataModel.getDatas()));
      }
    };
```
我们定义了一个Subscriber对象，在上篇中讲到了什么是Subscriber。这个是对数据流发射的相应，差不多对应了观察者模式中的观察者。当得到失败和成功的通知的时候，我们这里进行log的输出并且显示刷新的图标。当接收到数据的时候，我们就创建recyclerview的adapter，进行列表的填充显示。


### subscribe
一旦我们订阅，就会执行数据的发射，默认的情况下，没有订阅操作，数据是不会被发射的。
```java
    getDestinationDataObservable(url).subscribe(subscriber);
```
以上的操作就会完成订阅，正常的数据产生，发射，相应都会发生。但是，真当我们允许的时候，就会报错，原因是我们都知道，对UI的操作都必须在UI主线程中。

### subscribeOn && observeOn

指定数据产生发射的线程和订阅响应的线程。
 ```java
 getDestinationDataObservable(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
 ```
 subscribeOn表示observable执行所在的线程，这里指的是网络请求，请求的数据进行gson解析，再将数据发射的线程。
 observeOn表示的订阅响应的线程，这里指的是填充发射过来的数据到列表中。
 运行就能看到我们想要的效果。到此我们学会了最基本的一些使用，基本上掌握了这几个就可以轻松地展开工作了。当然还有很多其他的技巧。

### From && Just
我们刚刚有说过，数据流不一定是连续的，那么肯定存在连续的，连续不断的弹射，更符合官方文档那种弹珠示意图。from就是一个这样的操作符。
这个目前未想到在当前这个模块的应用场景。


### repeat
这个是重复，我们让当前的列表中的数据重复发送两次。
```java
getDestinationDataObservableByCreate(url).repeat(2)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
```
修改下subscriber
```java
@Override
            public void onNext(DestinationDataModel destinationDataModel) {
                if (destinationRecyclerView.getAdapter() == null) {
                    DestinationRecyclerAdapter adapter = new DestinationRecyclerAdapter(getActivity(), destinationDataModel.getDatas());
                    destinationRecyclerView.setAdapter(adapter);
                } else {
                    DestinationRecyclerAdapter adapter = (DestinationRecyclerAdapter) destinationRecyclerView.getAdapter();
                    adapter.datas.get(0).getInfos().addAll(destinationDataModel.getDatas().get(0).getInfos());
                }
            }
```
上面的结果是，请求两次网络。我们会看到数据重复了，同样的数据被发送了两次，并且是从头到尾的重复了两次。

### defer
延迟操作，等到订阅的时候再准备数据流。这里尤其对just和from操作符的效果最为明显,以下是国外的一个just的例子说明，from的原理一样。
[借用一个国外的例子](http://blog.danlew.net/2015/07/23/deferring-observable-code-until-subscription-in-rxjava/)
[国内的翻译](http://www.jianshu.com/p/c83996149f5b)
```java
public class SomeType {  
private String value;

public void setValue(String value) {
  this.value = value;
}

public Observable<String> valueObservable() {
  return Observable.just(value);
}
}
```
对于以下代码的调用会出现怎么样的结果呢？
```java
SomeType instance = new SomeType();  
Observable<String> value = instance.valueObservable();  
instance.setValue("Some Value");  
value.subscribe(System.out::println); //订阅的时候发射数据
```
如果你认为会打印出“Some Value”，那就错了。而实际打印结果是“null”。因为在调用Observable.just()的时候，value已经初始化了。
just()，from()这类能够创建Observable的操作符（译者注：创建Observable的操作符）在创建之初，就已经存储了对象的值，而不被订阅的时候。订阅的时候只是发射数据。
这种情况，显然不是预期表现，我想要的valueObservable()是无论什么时候请求，都能够表现为当前值。所以我们需要延迟数据的创建直到有人订阅。有两个方法，一个是用create自主创建，我们可以自己精确的控制发射什么，什么时候发射，还有一个是用的defer延迟操作符。defer()中的代码直到被订阅才会执行。我们只需要在请求数据的时候调用Observable.just()就行了，使用defer()操作符的唯一缺点就是，每次订阅都会创建一个新的Observable对象。create()操作符则为每一个订阅者都使用同一个函数，所以，后者效率更高。

因为我学习的时候，难以想清楚延迟和create操作符中的call的时间顺序和区别，我们用另外一个例子解释一下。
```java
private Observable<Integer> getInt() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        Log.e("GetInt", currentDateandTime);

        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(42);
                subscriber.onCompleted();
            }
        });
    }

    //simple defer
    private void simpleDefer() {
        //defer中的getInt操作等到有人订阅deferObservable的时候才会被执行
        //假如这里不用defer，直接用getInt返回，那么调用simpleDefer的时候就会打印时间
        deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return getInt();
            }
        });
//        deferObservable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("subscribe:" + integer);
//            }
//        });
    }
```
在不用延迟的情况下，我们调用simpleDefer返回一个数据流的时候就会打印时间，反之我们不用延迟的话，则会在调用simpleDefer的时候就已经打印了当前的时间。
所以，这里被延迟的是我们getInt被调用的时机。注意：create中的发射42和延迟 *无关*，这个call函数就是在 *发射* 数据，*订阅的时候才会发射数据* ，一旦订阅发生的额时候，就会发射42。

总之记住，defer延迟的是参数function中的操作。只要将需要延迟创建的操作放到function函数中即可。这个对于数据的新鲜度有要求的操作很有用。

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
 运行就能看到我们想要的效果。


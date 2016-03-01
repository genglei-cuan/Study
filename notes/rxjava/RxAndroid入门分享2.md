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

### 我们用项目的目的地来演示
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

```java



```

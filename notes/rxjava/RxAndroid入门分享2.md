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

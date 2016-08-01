#Gradle笔记

## 依赖
compile 编译依赖，最终会被注入在最终的apk文件中。
debugCompile: debug构建类型的编译

注意：作为库项目发布，默认的时候，发布的是release版本的,而不管我们选择的是什么版本，依赖他的项目是什么版本，目前这个是gradle的限制，
目前还在解决当中。

可以通过在库项目中声明。

```groovy
android {
    defaultPublishConfig "debug"
}
```
主要的问题在于，每个渠道都需要指定相应debug
```groovy
android {
    defaultPublishConfig "flavor1Debug"
}
```
所以，最终建议可以声明在主要module中。

## 文摘
在 gradle 中通过 plug 定义了任务。比如apply plugin: 'com.android.application'

---
title: AndroidStudio引入AAR依赖
date: 2016-08-10 00:25:57
tags: [aar]
---
# AndroidStudio如何引用aar依赖

google了一圈，网上的方法基本都是以下这种，在module/build.gradle文件中添加如下代码.同时将aar文件copy到libs包下.

```groovy

repositories {
    flatDir { dirs 'libs' }
}
compile(name:'aarName', ext:'aar')

```

我尝试了很多次,没有成功.采用了以下方法成功了,和上面的内容一致,只是位置不一样.

1. project目录下新建一个目录 aars(名字应用随意)

2. 在project下的build.gradle中添加代码.

```groovy
repositories {
    flatDir { dirs 'libs' }
}
```
3. 在该引用的地方添加引用,格式如下.

```groovy

compile(name:'aarNameWithoutExtention', ext:'aar')

```
采取如上步骤之后,即可成功添加依赖.



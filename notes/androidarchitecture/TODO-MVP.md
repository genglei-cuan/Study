# [TODO-MVP](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)

## 总概

这个例子是其他变种版本的基础。这个例子展示MVP模式的一个简单的实现，没有参杂其他的架构框架。
这个例子，使用手动注入依赖的方式来提供本地和远端的数据。异步的任务是通过callback实现的。

![示例图](https://github.com/googlesamples/android-architecture/wiki/images/mvp.png)

注意：在MVP的上下文中，属于view是被重新重载了。
android.view.View被称作"Android View",在MVP中接受presenter发送命令的view被简单的称为"view".

## Fragments

使用fragment有两个理由：
activity和fragment进行隔离，非常适合用来实现MVP。
- activity作为一个控制器，用来创建和控制view和presenter。
- 可以充分利用fragment框架进行平板和多屏幕适配。

## 关键概念

在这个APP中有四个特点：
任务
任务详细
添加编辑任务
数据统计

每个特点有:

- 约定view和presenter的定义
- activity负责产生fragment和presenter
- fragment实现view中的接口
- presenter实现presenter定义的接口

总之，业务逻辑在presenter中，并且依赖于实现UI工作的view。
view层几乎是不包含业务逻辑的，只负责将presenter中的UI指令转换成UI表现，并且监听用户的UI操作，然后传递给presenter层。
通过接口来约定view和presenter之间的连接。


## 依赖
- 常用的Android官方support包(com.android.support.*)
- Android测试包(Espresso, AndroidJUnitRunner…)
- Mockito
- Guava (null checking)

## 特点
### 复杂性 - 这个比较容易理解
#### frameworks/libraries/tools的框架使用

还没有

#### 概念复杂性
这个比较低，作为一个纯MVP实现。

### 可测试性
#### Unit testing

高，presenter可以作为仓库和数据源进行单元测试。

#### UI testing

高, 注入一个假的的module，允许进行假数据进行测试。

### Code metrics
和传统没有架构的项目相比，
这个例子简绍了额外的类和接口:presenter，仓库，接口等等，所以在MVP中无论是代码的行数还是类的数量都比较高。

### 维护性
#### 易于修改和添加新特性
高。
#### 学习成本
低。项目特点明确，责任清晰明确。开发人员不需要了解项目中的外部依赖。
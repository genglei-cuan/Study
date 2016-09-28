# Window简介

## 什么是 Window

Window 是一个窗口的概念，但是与我们想当然的不同，在 Android 里，Window 并不是一个像 View 一样"可见"的窗口。它本身是一个抽象的概念，在 Android 中，他的具体实现类是 PhoneWindow。

在 android 中任何视图要想展现出来,都需要依赖于 Window ,比如 activity ,dialog , toast.所以 window 和视图之间就会有扯不清的关系.


## WindowManager

Window 并不能被直接访问，Android 对外提供了 WindowManager ，用于提供给外界操作 Window 使用。


## Window 分类

- 应用类 Window .
- 子 Window, 不能独立存在,依赖于父 Window ,比如 Dialog.
- 系统 Window,需要相应的权限,比如 Toast ,系统状态栏.


### WindowManager 的操作
增删改操作. 这些操作的实体都是 View.

window 实质上是并不存在的,只有 view 是实质存在的.

WindowManager是一个接口,WindowManagerImpl 是实现类,采用桥接模式,委托 WindowManagerGlobal 进行具体的操作.

这些增删改,在 WindowManagerGlobal 中都有对应的方法.在 WindowManagerGlobal 保存了当前 WindowManager 管理的所有的 View ,所有的 ViewRootImpl 和所有的布局参数,以及正在被删除或者执行了删除操作,但还未被删除的view.


#### 增
WindowManagerImpl 的add 操作,委托给 WindowManagerGlobal 执行, WindowManagerGlobal 内部会创建 ViewRootImpl ,ViewRootImpl 执行对应的 setview 方法, 里面有 WindowSession 调用 WindowManagerService来添加view.

#### 删
WindowManagerImpl 的 remove 操作,委托给 WindowManagerGlobal 执行,内部 ViewRootImpl 有个 die 方法,分为同步删除和异步删除,对于异步删除的时候,在执行完die 方法后,会执行 dispatchDetachFromView 这里才是真正的删除 view 的操作. 在这个操作中会执行
- 垃圾回收.
- 删除view
- 调用view里关于detach的回调.
- WindowManagerGlobal 刷新数据

#### 改 
WindowManagerGlobal 调用 ViewRootImpl 去更新布局,然后进行view的重绘.然后调用 WindowSession 更新视图,再交由 WindowManagerService 进行操作.

### Winodw的创建过程

#### Activity

在activity 的attach 方法中,调用
 




 




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

在 Activity 的 attach 方法中,调用 PolicyManager 创建一个 Window  并设置好回调(Activity 实现了回调接口) ，在 Activity 的 setContentView  的时候，会委托 Window 的 setContentView 方法， 创建 DecorView 加载 Activity 的布局到 DecorView 中， 在 Activity 的 onResume 中调用 makeVisible ，这时候会调用 WindowManager 将 DecorView 进行添加操作，这时候才是可见的。

#### Dialog

与 Activity 的操作类似，有点注意的是，这个 WindowManager 管理 View 的操作是在 show 方法中。而且创建一个这个 window 需要的是 Activity 的 Context ，因为需要验证应用的 Token 。


#### Toast

Toast 创建显示的 View ，调用 NotificationManagerService 的 enqueueToast 进行添加排队，等待显示。在 enqueueToast 中会判断当前的 Toast 是不是系统的 Toast，
在添加到队列之后，enqueueToast 还会根据保存的 ToastRecord 的记录判断当前的 Toast 是否是已经存在的 ，存在的则更新信息，反之不存在的话就新增 ToastRecord 记录，ToastRecord 中含有这个 Toast 的回调等信息，并且判断当前以包名为单位存在的 Toast 个数是否超出了系统的限制，一般是50. 此时再判断当前的 Toast 是否是在队列的第一个，如果是，则调用 showNextToastLocked 进行显示。显示的时候，取出的依旧是 ToastRecord 对象，调用 ToastRecord 对象的回调，这个回调其实是一个 TN 对象，这个 TN 实则是 binder 对象，这里就有一个 IPC 操作。原因是这个 Toast 的显示需要在发起 Toast 的线程上显示。在显示完这个 Toast 之后，NotificationManagerService 会有一个延时操作。

Toast 的隐藏操作和显示操作类似，都是通过 TN 的回调执行，都是 IPC 过程，运行在 binder 线程池中，内部是通过 handler 实现线程的切换。TN 的显示和隐藏操作是通过 WindowManager 的 add 和 remove 操作实现的。

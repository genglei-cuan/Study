# Window简介

  ## 什么是 Window

Window 是一个窗口的概念，但是与我们想当然的不同，在 Android 里，Window 并不是一个像 view 一样"可见"的窗口。它本身是一个抽象的概念，在 Android 中，他的具体实现类是 PhoneWindow。

## WindowManager

Window 并不能被直接访问，Android 对外提供了 WindowManager ，用于提供给外界操作 Window 使用。

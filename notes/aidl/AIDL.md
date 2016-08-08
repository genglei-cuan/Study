# AIDL 介绍

## 服务端

服务端需要创建一个 service ，用来监听客户端的连接请求，然后创建一个 AIDL 文件；
将需要暴露给客户端的接口在 AIDL 文件中声明，最后，在 service 中实现这个接口即可。

## 客户端

客户端首先需要绑定服务端的 service ，绑定成功后，将服务端返回的 Binder 对象转换成  AIDL 接口所属的类型，
接着就可以调用 AIDL 中的方法。





# [Binder 笔记](http://blog.csdn.net/universus/article/details/6211589)

## Binder 通信模型:

Binder框架定义了四个角色：Server，Client，ServiceManager（以后简称SMgr）以及Binder驱动。其中 Server，Client，SMgr运行于用户空间，驱动运行于内核空间。这四个角色的关系和互联网类似：Server是服务器，Client是客户终 端，SMgr是域名服务器（DNS），驱动是路由器。


采用client-server模式。

Binder是一个实体对象，位于一个server中。该对象提供了一套方法用以实现对服务的请求，就象类的成员函数。

在client中有这个Binder对象的引用，透过这个引用，可以操作对服务的请求。


binder驱动，驱动负责进程之间Binder通信的建立，Binder在进程之间的传递，Binder引用计数管理，数据包在进程之间的传递和交互等一系列底层支持。

ServiceManager
和DNS类似，SMgr的作用是将字符形式的Binder名字转化成Client中对该Binder的引用，使得Client能够通过Binder名字获得对Server中Binder实体的引用。注册了名字的Binder叫实名Binder。
Server创建了Binder实体，为其取一个字符形式，可读易记的名字，将这个Binder连同名字以数据包的形式通过Binder驱动发送给 SMgr，通知SMgr注册一个名叫张三的Binder，它位于某个Server中。驱动为这个穿过进程边界的Binder创建位于内核中的实体节点以及 SMgr对实体的引用，将名字及新建的引用打包传递给SMgr。SMgr收数据包后，从中取出名字和引用填入一张查找表中。


binder驱动会自动帮SMGR进程创建一个binder实体对象，这个对象实体存放在SMGR中，这个地址默认是0，之后其他的server想要向SMGR注册binder的时候，直接通过0这个引用就可以和smgr进行通信。

要注意这里说的Client是相对SMgr而言的，一个应用程序可能是个提供服务的Server，但对SMgr来说它仍然是个Client。



Server向SMgr注册了Binder实体及其名字后，Client就可以通过名字获得该Binder的引用了。Client也利用保留的0号引用向 SMgr请求访问某个Binder：我申请获得名字叫张三的Binder的引用。SMgr收到这个连接请求，从请求数据包里获得Binder的名字，在查 找表里找到该名字对应的条目，从条目中取出Binder的引用，将该引用作为回复发送给发起请求的Client。



## Binder协议
Binder的协议的基本格式是命令+数据的方式，使用ioctl(fd, cmd, arg)函数实现交互。命令由参数cmd承载，数据由参数arg承载，随cmd不同而不同。


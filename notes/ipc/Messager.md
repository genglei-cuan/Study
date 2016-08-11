# Messenger

Messenger 底层是对AIDL做封装，这样可以更简单的进行进程间的通信。
由于每次只处理一个请求，所以就不用考虑线程同步的问题，因为服务端中不存在并发执行的情形。

## 实现步骤

1. 服务端进程
现在服务端创建一个 service 来处理客户端的连接请求，同时创建Handler ，通过handler创建一个Messenger对象，然后在service的onbind中返回这个messenger对象底层的binder即可。

2.客户端进程
客户端进程中，首先要绑定服务端的service，绑定成功后，用服务端返回的ibinder对象创建一个messenger，通过这个messenger就可以向服务端发送消息了，
发送消息类型为message对象。如果需要服务端能够回应客户端，就和服务端一样，我们还需要创建一个handler并创建一个新的messenger，并把这个messenger对象通过message的replyto 参数传递给服务端，
服务端通过这个replyto参数就可以回应客户端。


### Note

#### 简单的发送消息
messenger 传递消息是通过message对象来的，message 能使用的载体只有what，arg1，arg2，bundle以及replyTo。
message中的另外一个字段object在同一个进程中很实用，但是在进程间通信的时候，在2.2之前，是不支持object跨进程传输的。
不支持跨进程传输，即使2.2之后，也仅仅是系统提供的实现了parcelable接口的对象才能传输，也就是说，我们自定义的是实现不了的。
但是，bundle是不存在这个限制的。


##### 需要服务端对客户端进行相应



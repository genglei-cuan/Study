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



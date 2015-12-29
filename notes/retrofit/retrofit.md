#Retrofit的使用
------
[]()

> * retrofit的配置
> * retrofit的使用步骤


##retrofit的配置
在gradle中配置上
compile 'com.squareup.retrofit:retrofit:2.0.0-beta2'
compile 'com.squareup.retrofit:converter-gson:2.0.0-beta2' #使用Gson转化json串

##retrofit的使用步骤

1.构建Retrofit对象：Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(GsonConverterFactory.create()).build();
2.创建一个服务接口，在接口中定义请求的方法，所有的关于请求的参数和post的内容等设计到动态改变的，都是通过方法的参数指定，对于有些固定内容的，一般都是放在接口上方通过注解的形式表明；
3.通过服务创建一个call，调用call的方法进行请求。

##注意事项

Call是Retrofit中重要的一个概念，代表被封装成单个请求/响应的交互行为。
通过调用Retrofit2的execute（同步）或者enqueue（异步）方法，发送请求到网络服务器，并返回一个响应（Response）。

1.独立的请求和响应模块

1.从响应处理分离出请求创建

1.每个实例只能使用一次。

1.Call可以被克隆。

1.支持同步和异步方法。

1.能够被取消。

>*对于需要重新调用的请求可以clone再重新执行。

retrofit2也支持多个convert，现在不提供默认的convert，需要显示声明。
假如有多个序列化解析器，则需要将json的解析放在最后；因为其他类型的是通过继承关系来判断的，json比较特殊，
没有明显的继承关系，所以需要将json放在最后一个，不然任何一个均会被json的convert认为可以解析的.
在有需要用RxJava 来代替 call, 就需要一个 Call Adapter Factory:.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
Call Adapter Factory 是一个知道如何将 call 实例转换成其他类型的工厂类。
目前，我们只有 RxJava 的类型，也就是将 Call 类型转换成 Observable 类型。
如果你了解 RxJava, 其实还有一种新的 Observable 类型（一次只发射一个 item 的类型）。你可以用这个 call adapter factory 来转换到其中任意一种 Observable


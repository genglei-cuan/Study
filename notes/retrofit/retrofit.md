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


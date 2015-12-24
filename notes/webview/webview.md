#webview的使用总结
------
[webview是在应用内嵌入类似于浏览器的效果的控件。](http://developer.android.com/guide/webapps/index.html)

> * webview的简单使用
> * webview的一些坑


## [webview的简单使用](http://developer.android.com/guide/webapps/webview.html)

WebChromeClient：对于需要反馈到浏览器上的UI时，需要自定义一个WebChromeClient
WebViewClient：需要对于浏览器上的内容渲染产生影响的时候，需要自定义一个WebViewClient

DOM Storage 分为 sessionStorage 和 localStorage。DomStorage 适合存储比较简单的数据。
webSettings.setDomStorageEnabled(true);启用 Dom Storage。

H5 也提供基于 SQL 的数据库存储机制，用于存储适合数据库的结构化数据。
webSettings.setDatabaseEnabled(true);
webSettings.setDatabasePath("dbPath");

AppCache 看起来是一种比较好的缓存方法，除了缓存静态资源文件外，也适合构建 Web 离线 App。
HTML 在头中通过 manifest 属性引用 manifest 文件。manifest 文件，就是上面以 appcache 结尾的文件，是一个普通文件文件，列出了需要缓存的文件。
需要通过 Webview 设置接口启用 AppCache，同时还要设置缓存文件的存储路径，另外还可以设置缓存的空间大小。
webSettings.setAppCacheEnabled(true);
webSettings.setAppCachePath(cachePath);
webSettings.setAppCacheMaxSize(5*1024*1024);





## [webview的屏幕适配](http://developer.android.com/guide/webapps/targeting.html)
在Android原生的开发中需要适配不同的屏幕尺寸和分辨率，同样的在H5页面也是一样的，Android规定了一些特有的meta来约定这些适配。




## [兼容Android4.4的webview](http://developer.android.com/guide/webapps/migrating.html)
Android 4.4 (API level 19) 一种基于 Chromium的webview. 这个重大的升级，优化了webview对HTML5, CSS3, 以及 JavaScript的支持，以便适应最新的网络浏览器


### 2. 默认的zoom被废弃

getDefaultZoom() and setDefaultZoom() 这两个获取和设置初始缩放比例的方法在以后不再被支持，取而代之的是在网页中定义一个适当的viewport


## [Debugging WebApps](http://developer.android.com/guide/webapps/debugging.html)
可以在网页的JS中写入需要打印的log，这样可以在Android的logcat中输出，PS:此外此处还需要拓展一个远程调试的方法，结束chrome浏览器，可以在PC端的chrome浏览器中进行调试。







## [实践](http://developer.android.com/guide/webapps/best-practices.html)
本章讲述的是如何规范化的设计web  APP，包含服务器端的API设计，以及前端的web页面应该遵守的规范，如何加快响应速度，避免在head标签中加载不必要的文件，
相反的应该直接把CSS 和 JavaScript直接放在<head>中，对于scripts 并没有必要一定要在页面加载完成之后再加载。或者可以对于这些文件进行minify等方式的压缩。 








##webview中一些问题集锦
###认识误区一：
关于自定义WebViewClient，实现shouldOverrideUrlLoading()方法，关于返回值的问题，一般默认都是返回的false，
文档说是返回false代表交给默认的webview处理，application不处理，反之，application处理，不交给webview处理；
意思是，交给webview处理，就是你需要在webview加载这个URL之前需要进行其他操作，亦或者是直接截断了。
###白屏产生的原因之一
页面内跳转本身webview就会有白屏现象。
此外，一些H5页面有些内容是在页面加载成功之后，由JS进行填充的，这样，假如H5页面的框架是个空的，那么就会导致，
即是页面已经加载完了，但是页面的内容还没完全填充好，会产生白屏，这样轻则为长时间白屏，重则有闪屏的视觉效果。
###WebviewClient的几个回调函数
onPageStarted，当调用完loadURL的时候，就开始调用了，这个含义是请求HTTP就算是开始加载。
onLoadResource，加载请求中的数据，这个回调也是随着返回的报文数据块多次回调，只要有资源加载就会调用这个函数。无网络状态因为会加载提示页面，也会被调用。
onPageFinished，当HTTP请求结束之后，就会调用，这个时候表示请求结束了(目前感觉是这样，到底结束没结束，还不敢太肯定)，随后还有其他的资源待加载.
                同样的，这个也仅仅是表示一次请求结束，不代表请求成功，所以在无网络状态也是会调用的。
###WebChromeClient的几个回调函数
onProgressChanged，这个函数基本上和onPageStarted差不多时间开始启动，目前测出来是在onPageStarted之前，而且这个进度值是从10开始，不是0；
                   进度值100在onPageFinished()之前。PS：据实验证明，这个只是表示请求过程的进度，无论成功失败，都是一次请求，所以在无网络状态，
                   这个也能正常的返回调用。






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






#webview的使用总结
------
[webview是在应用内嵌入类似于浏览器的效果的控件。](http://developer.android.com/guide/webapps/index.html)

> * webview的简单使用
> * webview的一些坑


## [webview的简单使用](http://developer.android.com/guide/webapps/webview.html)

WebChromeClient：对于需要反馈到浏览器上的UI时，需要自定义一个WebChromeClient
WebViewClient：需要对于浏览器上的内容渲染产生影响的时候，需要自定义一个WebViewClient


## [webview的屏幕适配](http://developer.android.com/guide/webapps/targeting.html)





## [兼容Android4.4的webview](http://developer.android.com/guide/webapps/migrating.html)
Android 4.4 (API level 19) 一种基于 Chromium的webview. 这个重大的升级，优化了webview对HTML5, CSS3, 以及 JavaScript的支持，以便适应最新的网络浏览器

### 2. 默认的zoom被废弃

getDefaultZoom() and setDefaultZoom() 这两个获取和设置初始缩放比例的方法在以后不再被支持，取而代之的是在网页中定义一个适当的viewport


## [Debugging WebApps](http://developer.android.com/guide/webapps/debugging.html)


## [实践](http://developer.android.com/guide/webapps/best-practices.html)



## [实践博客](http://developer.android.com/guide/practices/index.html)



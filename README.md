# Study
    
    学习的demo，简单的一些概念性的东西，供自己熟悉API和概念使用。
    
-----------
# Project 结构说明
> * app   对应了主module
> * mylib 对应自己写的一些库
> * notes 对应自己写的笔记和博客文件


-----------
#  包名对应的功能内容

## App
- [ ]  activitylifecycle activity的生命周期的研究，待添加一些关于activity启动模式的demo TODO
- [x]  agera             google 官方的响应式库
- [x]  aidl              AIDL demo TODO
- [x]  alertDialog       测试警告对话框
- [x]  animator          属性动画的简单使用
- [x]  annotations       annotations的使用
- [x]  asyntask asyntask 异步任务的初步使用，模拟进度条
- [x]  audiomanager      静音，震动等模式的切换
- [x]  blurre            模糊效果，指定位置的背景模糊，即部分模糊
- [x]  bottombar       google新的设计规范，直接借用的[github例子](https://github.com/roughike/BottomBar)
- [x]  buttonmenu 仿造早期的UC浏览器的菜单
- [x]  buttomsheet   google官方设计指导下的bottomsheet，直接用的design包下的
- [x]  buttontext 仿造早期的UC浏览器的菜单，将菜单换成可滚动的文本
- [x]  brightness 调节屏幕亮度
- [x]  broadcast  研究广播的demo，有序广播，正常广播等的区别
- [x]  circle     圆形的钟盘，携带刻度，通过设置前后的时间，画出刻度
- [ ]  classloader     测试类加载器的例子 TODO
- [x]  click_xml 在XML文件中设置相应的方法
- [x]  contentobserver 监听文件的变化，这里为实现截屏文件的监听
- [x]  contentprovider ContentProvider 多进程数据共享的demo
- [x]  contextmean 测试context的真实含义
- [x]  customdialog 自定义各种形式的dialogue(熟悉stormzhang的)
- [x]  customview 自定义view,展示画布的含义
- [x]  dagger    dagger的简单demo
- [x]  datatraffic  流量，wifi开关的监听
- [x]  deep link    deep link 测试
- [x]  des   des加密
- [x]  deviceinfo   获取一些手机的基本硬件信息,以及一些屏幕的适配信息
- [x]  drawable    [自定义drawable,参考鸿洋博客](http://blog.csdn.net/lmj623565791/article/details/43752383)
- [x]  edittext   editext 测试内置的手机号验证
- [x]  eventbus   左右两个fragment进行通信,用的是鸿洋的demo
- [x]  fileIO   文件的IO操作
- [x]  flexbox   google flexlayout demo
- [x]  floatingview    仿造一些广告商的SDK中的一些悬浮窗，注：有的华为小米等可以禁止悬浮窗的显示的。
- [x]  floatLabelLayout
- [x]  flowLayout    仿造鸿洋大大的流式布局写的demo
- [x]  flyme    适配魅族的smartbar,按照官方文档，目前还没出现效果
- [x]  fragment   fragment的基本操作
- [ ]  fragmenttabhost   fragment的底部tab操作 TODO
- [x]  gallery   对gallery的基本操作和一个自定义的滑动日期选择
- [x]  gestureDetector   手势监听的demo
- [x]  gson    利用gson解析天气数据
- [x]  handler    handler post runnable的使用
- [x]  http    关于http的一些知识点,目前有asynchttp的demo
- [x]  image   图片显示的时候，先缩放再显示
- [x]  imageloader    universalimageloader 的demo,拷贝的官方lib中的sample，universalimageloader的基本操作
- [ ]  imagepicker    图片选择器,仿造微信做个一样的 TODO
- [x]  ipc            android ipc 场景测试
- [x]  layoutinflater 正解layoutinflater的使用及其参数的正确含义；
- [x]  layoutViewStub  layoutViewStub的使用
- [ ]  listview   listview的基本操作,listview缓存，以及一个通用的viewholder；自定义item的高度 ;添加郭林的下拉刷新;自己写的一个自定义下拉刷新 TODO
- [x]  log   研究一个漂亮点的log输出
- [ ]  materialdesign   材料设计 TODO
- [x]  menu   菜单案例
- [x]  mvp      MVP架构探索
- [x]  netstate   判断网络链接状态
- [x]  newsImoocListView   慕课网中的demo，解决listview异步加载图片错乱的问题。
- [x]  notification   在notification处显示通知，点击以后跳转到APP
- [x]  notificationDownload   在notification处显示下载进度
- [x]  numberFormat   解决西班牙等国外地区的一些小数点问题
- [x]  percentlayout   percentlayout的使用demo
- [x]  preferenceactivity   关于偏好设置的activity
- [x]  progressbar   圆形，带数字的进度条，参考的是代码家的
- [x]  pullscrollview    仿的微博个人中心的下拉效果
- [x]  pulltozoomviewdemo    为各种下拉添加head可以拉伸的demo，目前这个包里的都是别人的代码，直接拷贝的，略加修改的。
- [x]  radiobutton   radiobutton测试监听
- [x]  recyclerview   recyclerview的使用的简单demo,包含了设置监听器;添加了复杂类型的布局，多个item type;添加item动画；添加itemtouchhelper
- [x]  reflect      反射使用的demo
- [x]  Retrofit     Retrofit的使用demo
- [x]  rxandroid    rxandroid的使用demo
- [x]  scaletype     区分imageview中的比例类型的区别
- [x]  screenshot    屏幕截图测试监听，通过系统的内容观察者来监听
- [x]  service   简单测试service的生命周期，以及和activity之间的通信
- [ ]  share   内容分享的demo,分享到QQ，微信等的demo TODO
- [x]  signature   手写签名
- [x]  simpleWelcome   一个简单的欢迎界面，仿造的QQ
- [x]  spinnerdroplayout   spinner下拉选项
- [x]  staticactivity   测试静态代码块
- [ ]  strictmode   测试严格代码模式 TODO
- [x]  swiperefreshlayout   SwipeRefreshLayout下拉刷新控件
- [x]  toolbar   替代actionBar的toolbar 的demo
- [x]  touchdelegate   用于更改View的触摸区域。场景：比如在RecyclerView的ItemView里包含了CheckBox组件, 然后想实现点击ItemView的时候，也可以触发CheckBox，就可以使用此类。
- [x]  touchevent   touchevent，以及自定义view
- [x]  uicommunicate   在主线程上开辟线程
- [x]  viewcoordinate   android中的坐标系
- [x]  viewinjection    注解
- [x]  viewpager    viewpager的demo
- [x]  viewflipper   侧滑
- [x]  weakreferencehandler   初设弱引用概念
- [x]  webview   加载URI，以及注入JS调用和混淆代码
- [x]  wechat    仿造微信的聊天界面

## libsupport


::: info
* Foreground 判断当前APP处在前台和后台判断
* UILApplication 为了初始化UIL库所加的的Application
:::


##感激
感谢以下的作者,排名不分先后

* [Trinea](https://github.com/Trinea) 
* [代码家](https://github.com/daimajia)
* [鸿洋](http://blog.csdn.net/lmj623565791)
* [扔物线](https://github.com/rengwuxian)

##关于作者

```javascript
  var PersonalFile = {
    English Name  : "Steve Yan"
  }
```



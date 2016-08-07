# 开发小Tip1

## 查看activity启动的时间

之前想查看整个activity的启动时间都是去在代码中手动记录时间，在oncreate和onresume处记录时间，转换一下，打印出log。
其实系统每次启动一个activity，加载一个activity都会打印日志，直接过滤Displayed即可。

```java
07-04 14:04:25.066 925-965/? I/ActivityManager: Displayed com.android.settings/.applications.InstalledAppDetails: +1s57ms
07-04 14:04:45.770 925-965/? I/ActivityManager: Displayed com.gift.android/.activity.MainActivity: +1s806ms
```
后面的时间就是该activity的启动时间，当然如果你用ActivityManager过滤的话可以看到更加详细的细节，甚至发现一些不可思议的东西，比如，我发现，某些我未安装的软件的包名。哈哈。


## 查看 log 的命令

下面命令将只显示错误日志，和所有Tag＝mytag的调试日志，-C 会用不同颜色区分不同级别的日志，但只有Android 4.3以后才支持。

::: success
 adb logcat [-C] *:E <mytag>:D
:::


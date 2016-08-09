# AndroidTip1

## 模拟系统回收Activity
可以用来模拟内存重启
- 单进程
adb shell am force-stop [包名]

- 多进程
adb shell ps | grep [包名]
adb shell kill [PID]

## adb 启动指定的activity

adb shell am start  -n [包名]/[Activity名]

## (ClassyShark)[https://github.com/google/android-classyshark]
直接反编译apk文件


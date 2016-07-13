# TaskStackBuilder 简介

## 背景:
一个App通过通知的形式推广，点击通知，想要看到详情页，点击返回的时候，我们想让用户返回的是App的主页，而不是桌面。
以前的实现方式是通过记录当前的activity的方式实现的。现在可以通过TaskStackBuilder 来实现。
在网上查看了很多资料，发现运行之后的效果都不是预想的。

1.首先创建一个点击了notification之后跳转到的详情页的intent对象。
```java
 Intent resultIntent = new Intent(this, ResultActivity.class);
```
2.在manifest中声明详情页ResultActivity的栈中前一个activity。
```xml
<activity
      android:name="cn.steve.notification.ResultActivity"
      android:parentActivityName="cn.steve.notification.NotificationHandlerActivity">
      <meta-data
            android:name="android.support.PARENT_ACTIVITY"
            android:value="cn.steve.notification.NotificationHandlerActivity"/>
</activity>
```
3. 创建一个PendingIntent对象，这个是创建notification的必备。

```java
 PendingIntent pendingIntent = TaskStackBuilder.create(this)
            .addNextIntentWithParentStack(resultIntent)
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
```

4. 剩下的就是正常的启动一个notification了。
```java
        // new notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_email);
        mBuilder.setContentTitle("My Notification!");
        mBuilder.setContentText("Hello World!");
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
```
package cn.steve.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * 点击notification进入一个activity界面，按下返回键的时候，回到主界面，类似于QQ
 *
 * @author Steve
 */
public class TaskStackActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);

        // Use TaskStackBuilder to build the back stack and get the PendingIntent
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
            // add all of DetailsActivity's parents to the stack, followed by DetailsActivity itself
            .addNextIntentWithParentStack(resultIntent)
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // new notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_email);
        mBuilder.setContentTitle("My Notification!");
        mBuilder.setContentText("Hello World!");
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }
}

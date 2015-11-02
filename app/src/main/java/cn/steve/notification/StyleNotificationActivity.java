package cn.steve.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import cn.steve.study.R;


public class StyleNotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylenotification);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.ic_dialog_map)
                        .setContentTitle("Event tracker").setContentText("Event Receiced");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        inboxStyle.setBigContentTitle("Event tracker details");
        for (int i = 0; i < events.length; i++) {
            events[i] = "events" + i;
            inboxStyle.addLine(events[i]);
        }
        mBuilder.setStyle(inboxStyle);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }
}

package cn.steve.notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import cn.steve.study.R;


public class NotificationHandlerActivity extends Activity implements OnClickListener {

    NotificationHandler nHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationhandler);
        nHandler = NotificationHandler.getInstance(this);
        findViewById(R.id.simple_notification).setOnClickListener(this);
        findViewById(R.id.big_notification).setOnClickListener(this);
        findViewById(R.id.progress_notification).setOnClickListener(this);
        findViewById(R.id.button_notifcation).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_notification:
                nHandler.createSimpleNotification(this);
                break;
            case R.id.big_notification:
                nHandler.createExpandableNotification(this);
                break;
            case R.id.progress_notification:
                nHandler.createProgressNotification(this);
                break;
            case R.id.button_notifcation:
                nHandler.createButtonNotification(this);
                break;
            default:
                break;
        }
    }

}

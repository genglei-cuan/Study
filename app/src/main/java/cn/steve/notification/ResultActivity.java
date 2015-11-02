package cn.steve.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

import cn.steve.study.R;


public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskstackbuilder);
        //打开之后,就取消显示的notification
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(0);
    }
}

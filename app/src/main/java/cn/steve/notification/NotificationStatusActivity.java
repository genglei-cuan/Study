package cn.steve.notification;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by steveyan on 16-10-14.
 */

public class NotificationStatusActivity extends AppCompatActivity {

    private Button buttonMain;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        assignViews();
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > 18) {
                    textView.setText(isEnable() + "");
                } else {
                    textView.setText(NotificationsUtils.isNotificationEnabled(NotificationStatusActivity.this) + "");
                }


            }
        });
    }


    private void assignViews() {
        buttonMain = (Button) findViewById(R.id.buttonMain);
        textView = (TextView) findViewById(R.id.textView);
    }

    /**
     * in this Google I/O 2016 video.this method have to be called after api 19
     */
    private boolean isEnable() {
        return NotificationManagerCompat.from(this).areNotificationsEnabled();
    }


}

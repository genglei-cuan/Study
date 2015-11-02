package cn.steve.notificationDownload;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.steve.study.R;


public class NotificationDownloadActivity extends Activity {

    public static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_download);
    }

    public void clicklala(View view) {
        new NotificationDownloadTask(this, id++, "智能插件下载", R.drawable.ic_launcher)
                .execute("http://www.coolauncher.cn/zen/NotificationService.apk");
    }

}

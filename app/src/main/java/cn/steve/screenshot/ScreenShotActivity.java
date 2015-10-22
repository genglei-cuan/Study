package cn.steve.screenshot;

import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;

/**
 *
 * 截图监听器测试
 *
 * Created by yantinggeng on 2015/10/21.
 */
public class ScreenShotActivity extends AppCompatActivity {

    ScreenShotContentObserver observer;
    private TextView screenshot;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            screenshot.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);
        this.screenshot = (TextView) findViewById(R.id.screenshot);
        observer = new ScreenShotContentObserver(this.getApplicationContext(), handler);
        getContentResolver()
            .registerContentObserver(Media.EXTERNAL_CONTENT_URI, true, this.observer);
    }

    //方案1:直接观察目录的变化，前提是已知截图存放的位置
    private void plan1() {
        String path = "/mnt/sdcard/Pictures/Screenshots";
        screenshot.setText(path);
        FileObserver fileObserver = new FileObserver(path, FileObserver.CREATE) {
            @Override
            public void onEvent(int event, String path) {
                handler.obtainMessage(1, path).sendToTarget();
            }
        };
        fileObserver.startWatching();
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}

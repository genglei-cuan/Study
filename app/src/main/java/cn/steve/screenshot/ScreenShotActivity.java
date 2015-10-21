package cn.steve.screenshot;

import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by yantinggeng on 2015/10/21.
 */
public class ScreenShotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path =Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "Screenshots";

        FileObserver fileObserver = new FileObserver(path, FileObserver.CREATE) {
            @Override
            public void onEvent(int event, String path) {
                System.out.printf("截屏了：" + path);
            }
        };

        fileObserver.startWatching();
    }

}

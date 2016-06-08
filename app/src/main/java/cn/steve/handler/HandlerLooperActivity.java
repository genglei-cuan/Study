package cn.steve.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/8.
 */
public class HandlerLooperActivity extends AppCompatActivity {

    private static final String TAG = "HandlerLooperActivity";

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        Log.e(TAG, "onCreate: " + handler.getLooper().toString());
        newLooper4Thread();
    }

    private void newLooper4Thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                String s = handler.getLooper().toString();
                Log.e(TAG, "run: Thread" + s);
                Log.e(TAG, "run: MainLooper" + Looper.getMainLooper().toString());
                Log.e(TAG, "run: myLooper" + Looper.myLooper().toString());
            }
        }).start();
    }
}

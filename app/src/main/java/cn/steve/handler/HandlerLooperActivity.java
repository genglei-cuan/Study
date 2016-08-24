package cn.steve.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/8.
 */
public class HandlerLooperActivity extends AppCompatActivity {

    private static final String TAG = "HandlerLooperActivity";

    private Handler handler = new Handler();
    private TextView textViewMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        textViewMain = (TextView) findViewById(R.id.textViewMain);
        Log.e(TAG, "onCreate: getLooper():" + handler.getLooper().toString());
        Log.e(TAG, "onCreate: Thread :" + Thread.currentThread());
        newLooper4Thread();
    }

    private void newLooper4Thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: Thread before prepare():" + Thread.currentThread());
                Looper.prepare();
                Handler handler = new Handler();
                String s = handler.getLooper().toString();
                Log.e(TAG, "run: Thread getLooper():" + s);
                Log.e(TAG, "run: Thread getMainLooper():" + Looper.getMainLooper().toString());
                Log.e(TAG, "run: Thread myLooper():" + Looper.myLooper().toString());
                Log.e(TAG, "run: Thread between prepare():" + Thread.currentThread());
                textViewMain.setText(Thread.currentThread().toString());
                Looper.loop();
                Log.e(TAG, "run: Thread after prepare():" + Thread.currentThread());
            }
        }).start();
    }
}

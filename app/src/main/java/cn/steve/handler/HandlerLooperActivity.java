package cn.steve.handler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/8.
 */
public class HandlerLooperActivity extends AppCompatActivity {

    private static final String TAG = "HandlerLooperActivity";
    private TextView textViewMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        textViewMain = (TextView) findViewById(R.id.textView);
        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newLooper4Thread();
            }
        });
    }

    private void newLooper4Thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: " + Thread.currentThread().toString());
                textViewMain.setText("大写的服!!!!!!");
            }
        }).start();
    }
}

package cn.steve.ipc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/27.
 */
public class IPCSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
        assert textViewMain != null;
        String s = Runtime.getRuntime().toString() + "\n userid:" + UserManager.sUserId;
        textViewMain.setText(s);
    }
}

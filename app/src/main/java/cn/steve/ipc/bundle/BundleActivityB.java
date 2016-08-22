package cn.steve.ipc.bundle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/22.
 */
public class BundleActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        TextView textView = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getBundleExtra("bundle");
        System.out.println("Bundle:" + extras.toString());
        System.out.println("Shuaige:" + extras.get("extra").toString());
    }
}

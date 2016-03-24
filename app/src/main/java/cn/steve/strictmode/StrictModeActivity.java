package cn.steve.strictmode;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.BuildConfig;

/**
 * Created by yantinggeng on 2016/3/24.
 */
public class StrictModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            //enable strictMode
            StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }
}

package cn.steve.ipc.remoteprogress;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.steve.study.R;
import steve.cn.mylib.commonutil.ToastUtil;

/**
 * Created by yantinggeng on 2016/5/27.
 */
public class IPCMainActivity extends AppCompatActivity {

    private boolean serviceisRunning = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        UserManager.sUserId = 100;
        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceisRunning) {
                    SharedPreferences sp = IPCMainActivity.this.getSharedPreferences("data", Context.MODE_PRIVATE);
                    String string = sp.getString("ThirdUtil", "");
                    ToastUtil.show(IPCMainActivity.this, string, Toast.LENGTH_SHORT);
                } else {
                    startService(new Intent(IPCMainActivity.this, ThirdPartyService.class));
                    serviceisRunning = true;
                }
                //startActivity(new Intent(IPCMainActivity.this, IPCSecondActivity.class));
            }
        });
    }
}

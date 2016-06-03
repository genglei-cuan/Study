package cn.steve.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/27.
 */
public class IPCMainActivity extends AppCompatActivity {

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
                startService(new Intent(IPCMainActivity.this, ThirdPartyService.class));
                //startActivity(new Intent(IPCMainActivity.this, IPCSecondActivity.class));
            }
        });
    }
}

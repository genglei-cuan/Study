package cn.steve.forebackground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/27.
 */
public class ForeGroundMainActivity extends AppCompatActivity {

    private android.widget.Button buttonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        this.buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForeGroundMainActivity.this, ForeGroundActivityA.class));
            }
        });
    }
}

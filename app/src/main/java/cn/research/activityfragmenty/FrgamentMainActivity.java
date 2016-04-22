package cn.research.activityfragmenty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/4/22.
 */
public class FrgamentMainActivity extends AppCompatActivity {

    private Button buttonMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        buttonMain = (Button) findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrgamentMainActivity.this.startActivity(new Intent(FrgamentMainActivity.this, ActivityFragmentRelation.class));
            }
        });
    }
}


package cn.steve.layoutinflater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.steve.study.R;

/**
 * 测试，加载获取一些被gone的控件
 *
 *
 * 获取控件和设置控件的相应，与控件的可见性无关
 *
 *
 * Created by yantinggeng on 2016/2/16.
 */
public class InflaterGoneViewActivity extends AppCompatActivity {

    private Button goneButton, visibleButton;
    private LinearLayout goneLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflategoneview);
        this.goneButton = (Button) findViewById(R.id.goneButton);
        this.visibleButton = (Button) findViewById(R.id.visibleButton);
        this.goneLinearLayout = (LinearLayout) findViewById(R.id.goneLinearLayout);
        this.goneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InflaterGoneViewActivity.this, "哈喽", Toast.LENGTH_SHORT).show();
            }
        });
        visibleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}

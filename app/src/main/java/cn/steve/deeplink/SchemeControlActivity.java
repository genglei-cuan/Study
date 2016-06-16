package cn.steve.deeplink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/16.
 */
public class SchemeControlActivity extends AppCompatActivity {

    private android.widget.Button buttonMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        this.buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("lvmama://m.lvmama.com/webapp/product/364429?refer=pinyou&losc=088095"));
                startActivity(intent);
            }
        });
    }
}

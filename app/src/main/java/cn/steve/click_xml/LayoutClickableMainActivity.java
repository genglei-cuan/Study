package cn.steve.click_xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.steve.study.R;

/**
 * 对于设置selector，当手指在区域内move，滑出区域的时候，则失去焦点。
 *
 *
 * Created by yantinggeng on 2015/10/28.
 */
public class LayoutClickableMainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutClickable1;
    private Button ButtonClickable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutclickable);
        this.ButtonClickable2 = (Button) findViewById(R.id.ButtonClickable2);
        this.linearLayoutClickable1 = (LinearLayout) findViewById(R.id.linearLayoutClickable1);


    }
}

package cn.steve.share.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

public class AnFQNumEditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_fqnum_edit_text);
        AnFQNumEditText anetDemo = (AnFQNumEditText) findViewById(R.id.anetDemo);
        anetDemo.setEtHint("内容")//设置提示文字
            .setEtMinHeight(200)//设置最小高度，单位px
            .setLength(50)//设置总字数
            .setType(AnFQNumEditText.SINGULAR)//TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
            .setLineColor("#3F51B5")//设置横线颜色
            .show();
    }
}

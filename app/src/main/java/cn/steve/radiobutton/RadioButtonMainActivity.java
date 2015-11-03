package cn.steve.radiobutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.steve.study.R;

/**
 * 测试radioButton的使用
 *
 * Created by yantinggeng on 2015/10/12.
 */
public class RadioButtonMainActivity extends AppCompatActivity {

    private RadioGroup radioGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //这个设置会让默认的radioButton的回调调用两次，避免这个问题的方式是获取radioButton的对象，设置选中为true
        //radioGroup.check(R.id.radioButton);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * 当单选按钮的状态发生变化的时候才会被调用
             * @param radioGroup 外层的group
             * @param checkedId 被选中的单选按钮的ID
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
                Toast.makeText(RadioButtonMainActivity.this, radioButton.getText(),
                               Toast.LENGTH_SHORT).show();
            }
        });
    }
}

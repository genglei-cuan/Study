package cn.steve.reflect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/12.
 */
public class ReflectMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttonMain)
    void onClick() {
        try {
            Class<?> activity = Class.forName("cn.steve.reflect.ReflectSubActivity");
            startActivity(new Intent(this, activity));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

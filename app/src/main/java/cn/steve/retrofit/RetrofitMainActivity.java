package cn.steve.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/28.
 */
public class RetrofitMainActivity extends AppCompatActivity {

    String API = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }
}

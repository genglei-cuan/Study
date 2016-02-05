package cn.steve.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import cn.steve.study.R;


public class TranslucentBarImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_translucent_bar);
    }
}

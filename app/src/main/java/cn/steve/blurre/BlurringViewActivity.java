package cn.steve.blurre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.steve.study.R;
import steve.cn.mylib.blur.BlurringView;

/**
 * Created by yantinggeng on 2015/12/4.
 */
public class BlurringViewActivity extends AppCompatActivity {

    private BlurringView mBlurringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurringview);
        mBlurringView = (BlurringView) findViewById(R.id.blurring_view);
        View blurredView = findViewById(R.id.blurredView);
        mBlurringView.setBlurredView(blurredView);
        mBlurringView.invalidate();
    }
}

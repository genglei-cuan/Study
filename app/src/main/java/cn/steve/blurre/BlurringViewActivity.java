package cn.steve.blurre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;
import steve.cn.mylib.blur.BlurringView;

/**
 * Created by yantinggeng on 2015/12/4.
 */
public class BlurringViewActivity extends AppCompatActivity {

    View blurredView;
    private BlurringView mBlurringView;
    private boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurringview);
        ButterKnife.bind(this);
        mBlurringView = (BlurringView) findViewById(R.id.blurring_view);
        blurredView = findViewById(R.id.blurredView);
        mBlurringView.setBlurredView(blurredView);
    }

    @OnClick(R.id.blurredViewButton)
    void blurView() {
        mBlurringView.invalidate();
    }

    @OnClick(R.id.blurredViewButtonChangeBackground)
    void changeBackground() {
        int sdk = android.os.Build.VERSION.SDK_INT;
        int id;
        if (isChanged) {
            id = R.drawable.xihu;
            isChanged = false;
        } else {
            id = R.drawable.horse;
            isChanged = true;
        }
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            blurredView.setBackgroundDrawable(getResources().getDrawable(id));
        } else {
            blurredView.setBackground(getResources().getDrawable(id));
        }
    }


}

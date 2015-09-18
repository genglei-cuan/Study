package cn.steve.simpleWelcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import cn.steve.study.R;

/**
 * 欢迎界面，2秒之后自动消失，跳转到主登陆界面
 *
 * @author steve.yan
 */
public class WelcomeActivity extends Activity {

    protected static final String TAG = "WelcomeActivity";

    private Context mContext;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_first);
        mContext = this;
        findView();
        init();

    }

    private void findView() {
        mImageView = (ImageView) findViewById(R.id.iv_welcome);
    }

    private void init() {
        // 动画在两秒之后自动消失
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 跳转到登陆界面
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                // 结束当前的界面
                finish();
            }
        }, 2000);

    }
}

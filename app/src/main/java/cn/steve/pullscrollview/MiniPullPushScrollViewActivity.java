package cn.steve.pullscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/30.
 */
public class MiniPullPushScrollViewActivity extends AppCompatActivity {

    private MiniPullPushScrollView miniPullPushScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minpullpushscrollview);
        miniPullPushScrollView = (MiniPullPushScrollView) findViewById(R.id.miniScrollView);

    }
}

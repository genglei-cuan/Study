package cn.steve.floatingview;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

/**
 * 仿造广告商的SDK
 * <p/>
 * Created by Steve on 2015/8/27.
 */
public class FloatingMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        ButterKnife.bind(this);
        //展示侧边栏
        WindowUtils.initView(this.getApplicationContext());
        WindowUtils.showSidebar(this.getApplicationContext());
        WindowUtils.loadSlideBarURL("https://www.baidu.com/");
    }

    @OnClick(R.id.button)
    void removeTheView() {
        if (WindowUtils.isCenterShow()) {
            WindowUtils.hideCenter(this.getApplicationContext());
            WindowUtils.showSidebar(this.getApplicationContext());
            WindowUtils.loadSlideBarURL("https://www.baidu.com/");
        } else if (WindowUtils.isSlideBarShow()) {
            WindowUtils.hideSidebar(this.getApplicationContext());
            //展示中间区域的广告
            WindowUtils.ShowCenterAD(this.getApplicationContext());

            WindowUtils.setImageViewIconBitmap(new BitmapDrawable(getResources(), BitmapFactory
                    .decodeResource(getResources(), R.drawable.circle)));
            WindowUtils.setImageViewSafe(new BitmapDrawable(getResources(), BitmapFactory
                    .decodeResource(getResources(), R.drawable.circle)));
            WindowUtils.setTextViewAppName("天天消小鸟2");
            WindowUtils.setTextViewAppPopular("557万次下载 5.71MB");
            WindowUtils.setTextViewAppDesc("酷炫连消，经典玩法");
            WindowUtils.loadCenterURL("https://www.baidu.com/");
        }


    }


}

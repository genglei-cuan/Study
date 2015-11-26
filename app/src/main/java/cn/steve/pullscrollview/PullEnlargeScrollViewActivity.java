package cn.steve.pullscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import cn.steve.study.R;

/**
 * 下拉方法背景图片
 *
 *
 * Created by yantinggeng on 2015/11/26.
 */
public class PullEnlargeScrollViewActivity extends AppCompatActivity {

    private PullEnlargeScrollView mScrollView;
    private ImageView mHeadImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullenlargescrollview);
        mScrollView = (PullEnlargeScrollView) findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) findViewById(R.id.background_img);
        mScrollView.setHeader(mHeadImg);
    }


}

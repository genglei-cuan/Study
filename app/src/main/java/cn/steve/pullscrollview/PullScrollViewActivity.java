package cn.steve.pullscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TableLayout;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/25.
 */
public class PullScrollViewActivity extends AppCompatActivity
    implements PullScrollView.OnTurnListener {

    private PullScrollView mScrollView;
    private ImageView mHeadImg;

    private TableLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullscrollview);

        mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) findViewById(R.id.background_img);
        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);

    }

    @Override
    public void onTurn() {

    }
}

package cn.steve.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

/**
 * 分享到第三方社交平台 微信好友、微信朋友圈、微博、
 *
 *
 *
 * Created by yantinggeng on 2015/10/8.
 */
public class ShareMainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.buttonShare)
    void showShare() {
        //这边应该只有开启dialog和封装一些基本的信息，将过多的行为逻辑放置于util中
        
    }

}

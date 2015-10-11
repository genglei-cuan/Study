package cn.steve.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.steve.study.R;

/**
 * 分享到第三方社交平台 微信好友、微信朋友圈、微博、
 *
 *
 *
 * Created by yantinggeng on 2015/10/8.
 */
public class ShareMainActivity extends AppCompatActivity {

    @Bind(R.id.buttonShareWeiXinFriend)
    Button buttonShareWeiXinFriend;
    @Bind(R.id.buttonShareWeiXinTimeLine)
    Button buttonShareWeiXinTimeLine;
    @Bind(R.id.buttonShareWeiBo)
    Button buttonShareWeiBo;
    @Bind(R.id.buttonShareQQFriend)
    Button buttonShareQQFriend;
    @Bind(R.id.buttonShareQQZone)
    Button buttonShareQQZone;
    private String text = "it is a test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }


}

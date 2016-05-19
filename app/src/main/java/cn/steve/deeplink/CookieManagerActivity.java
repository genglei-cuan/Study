package cn.steve.deeplink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/18.
 */
public class CookieManagerActivity extends AppCompatActivity {

    private TextView textViewMain;
    private String url = "http://www.baidu.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        textViewMain.setText(getCookie());
    }


    String getCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        return cookieManager.getCookie(url);
    }

}

package cn.steve.webview.advanced;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/24.
 */
public class AdvancedWebViewTestActivity extends AppCompatActivity
    implements MyWebViewClient.LoadListener {

    private WebView webView;
    private LinearLayout layout_loading;
    private ImageView anim_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_advanced);
        this.webView = (WebView) findViewById(R.id.advancedWebView);
        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);
        anim_img = (ImageView) findViewById(R.id.anim_img);
        layout_loading.setVisibility(View.GONE);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkLoads(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        MyWebViewClient client = new MyWebViewClient();
        client.setmLoadListener(this);
        webView.setWebViewClient(client);
        webView.loadUrl(
            "http://m.lvmama.com/app740/destination/#/destination?hideAppHeader=1&id=3984&toDest=%E6%B8%85%E8%BF%88&firstChannel=ANDROID&formate=json&globalLatitude=31.237465&osVersion=5.1&lvversion=7.4.1&udid=353347060560396&globalLongitude=121.382673&secondChannel=FENZHONG");
    }


    public void stopAnimation() {
        AnimationDrawable anim = (AnimationDrawable) anim_img.getBackground();
        if (anim.isRunning()) {
            anim.stop();
        }
        layout_loading.setVisibility(View.GONE);
    }

    private void startAnimation() {
        AnimationDrawable anim = (AnimationDrawable) anim_img.getBackground();
        if (!anim.isRunning()) {
            anim.start();
        }
    }

    private void startShow() {
        layout_loading.setVisibility(View.VISIBLE);
        startAnimation();
    }

    private void finishShow() {
        stopAnimation();
    }


    @Override
    public void onPageStart() {
        startShow();
    }

    @Override
    public void onPagefinish() {
        finishShow();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView != null) {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            ivStop();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void ivStop() {
        webView.getSettings().setBlockNetworkLoads(true);
        webView.getSettings().setBlockNetworkImage(true);
        webView.stopLoading();
    }
}

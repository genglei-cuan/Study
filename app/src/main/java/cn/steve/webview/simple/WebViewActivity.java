package cn.steve.webview.simple;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * @author Steve
 */
public class WebViewActivity extends Activity {

    private String url = "http://www.google.com";
    private Button back = null;
    private TextView textview = null;
    private Button refresh;
    private WebView mWebView;
    private TextView tv_error;
    private Handler mHanlder = new Handler() {
        public void handleMessage(Message msg) {
            CookieSyncManager.createInstance(WebViewActivity.this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie(url, msg.obj.toString());
            cookieManager.setAcceptCookie(true);
            CookieSyncManager.getInstance().sync();
            mWebView.loadUrl(url);
        }

        ;
    };

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.webView1);
        back = (Button) findViewById(R.id.back);
        refresh = (Button) findViewById(R.id.refresh);
        textview = (TextView) findViewById(R.id.title);
        tv_error = (TextView) findViewById(R.id.textview_error);
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        refresh.setOnClickListener(listener);
        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 允许服务器端的JS代码调用WebHost对象中的方法
        mWebView.addJavascriptInterface(new WebHost(this), "js");

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                textview.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        //当设置了自定义的WebViewClient之后，就表示不回去调用系统的浏览器
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {

                // view.loadUrl("file//android_asset/error.html");
                tv_error.setText("404000000000");
                System.out.println("404");
                mWebView.setVisibility(View.GONE);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        mWebView.setDownloadListener(new MyDownloadListener());
        new HttpCookie(mHanlder).start();
    }


    //解决加载卡顿的问题，在小米4上容易浮现
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void disableHardWareAcc() {
        try {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.refresh:
                    mWebView.reload();
                    break;
                case R.id.back:
                    WebViewActivity.this.finish();
                    break;
            }
        }
    }

    class MyDownloadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String arg0, String arg1, String arg2, String arg3, long arg4) {
            System.out.println(arg0);
            if (arg0.endsWith(".apk")) {
                // 1.采用自定义的方式进行下载
                // new HttpDownload(arg0).start();
                try {
                    // 2.采用系统的方式进行下载
                    Uri uri = Uri.parse(arg0);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

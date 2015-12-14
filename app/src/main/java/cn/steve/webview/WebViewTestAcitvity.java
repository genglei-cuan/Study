package cn.steve.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.steve.study.R;


/**
 * 测试几个WebView
 *
 * Created by yantinggeng on 2015/12/14.
 */
public class WebViewTestAcitvity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_test);
        this.webView = (WebView) findViewById(R.id.webView);
        //要有UI方面的交互，需要自定义一个WebChromeClient
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        //要能JS交互，需要设置为true
        webSettings.setJavaScriptEnabled(true);
        //Note that injected objects will not appear in JavaScript until the page is next (re)loaded
        webView.addJavascriptInterface(new JsObject(), "injectedObject");
        webView.loadData("", "text/html", null);
        webView.loadUrl("javascript:alert(injectedObject.toString())");
    }

    private void loadLocalHTML() {
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.loadUrl("file:///android_asset/test.html");
    }

    class JsObject {

        @JavascriptInterface
        public String toString() {
            System.out.println("it is from java object");
            return "it is from java object";
        }
    }

}

package com.steve.advanced.hybrid;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 自定义的 WebView
 *
 * Created by steveyan on 16-10-9.
 */

public class HybridWebView extends WebView {

    public HybridWebView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);

        WebSettings webSettings = getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDatabaseEnabled(true);//开启database storage API功能
        webSettings.setDomStorageEnabled(true);//开启DOM storage API功能
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true);
        }
        this.setWebViewClient(new HybridWebViewClient());
        this.addJavascriptInterface(new JSInterface(), "javaHandlerForJS");
    }
}

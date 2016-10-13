package com.steve.advanced.hybrid;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * webview 的交互客户端
 *
 * Created by steveyan on 16-10-9.
 */

public class HybridWebViewClient extends WebViewClient {

    private URLInterceptor interceptor;

    public HybridWebViewClient() {
        this.interceptor = new URLInterceptor();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        boolean intercepted = interceptor.intercept(url);
        if (intercepted) {
            return intercepted;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

}

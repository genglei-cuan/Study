package cn.steve.webview.advanced;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by yantinggeng on 2015/12/24.
 */
class MyWebViewClient extends WebViewClient {

    private static final String TAG = "MyWebViewClient";

    private LoadListener mLoadListener;

    public LoadListener getmLoadListener() {
        return mLoadListener;
    }

    public void setmLoadListener(LoadListener mLoadListener) {
        this.mLoadListener = mLoadListener;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        Log.d(TAG, "onLoadResource() called with: " + "view = [" + view + "], url = [" + url + "]");
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        view.getSettings().setBlockNetworkImage(true);
        mLoadListener.onPageStart();
        Log.d(TAG, "onPageStarted() called with: " + "view = [" + view + "], url = [" + url+ "], favicon = [" + favicon + "]");
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description,
                                String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.getSettings().setBlockNetworkImage(false);
        super.onPageFinished(view, url);
        mLoadListener.onPagefinish();
        Log.d(TAG, "onPageFinished() called with: " + "view = [" + view + "], url = [" + url + "]");
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    public interface LoadListener {

        void onPageStart();

        void onPagefinish();
    }


}

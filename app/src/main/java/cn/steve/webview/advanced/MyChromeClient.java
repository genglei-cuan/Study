package cn.steve.webview.advanced;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by yantinggeng on 2015/12/24.
 */
public class MyChromeClient extends WebChromeClient {

    private static final String TAG = "MyChromeClient";
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        Log.d(TAG, "onProgressChanged() called with: " + "view = [" + view + "], newProgress = ["+ newProgress + "]");
    }


}

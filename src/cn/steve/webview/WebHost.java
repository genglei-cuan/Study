package cn.steve.webview;

import android.content.Context;
import android.widget.Toast;

/**
 * 混淆的时候，JS会无法调用这个方法，需要在proguard-project设置不被混淆
 * @author Steve
 *
 */
public class WebHost {
  public Context mContext;

  public WebHost(Context context) {
    this.mContext = context;
  }

  public void callJS() {
    Toast.makeText(mContext, "call from", Toast.LENGTH_LONG).show();
  }
}

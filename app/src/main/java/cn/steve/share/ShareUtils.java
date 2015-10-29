package cn.steve.share;

import android.app.Activity;

/**
 * Created by yantinggeng on 2015/10/16.
 */
public class ShareUtils {

    public static void start(Activity activity, ShareMessage message) {
        final ShareDialog dialog = new ShareDialog(activity);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle("Title");
        dialog.show();
    }

}

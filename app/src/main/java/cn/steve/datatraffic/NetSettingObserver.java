package cn.steve.datatraffic;

import android.content.Context;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by Steve on 2015/7/28.
 */
public class NetSettingObserver extends ContentObserver {

    private Context mContext;

    public NetSettingObserver(Context context) {
        super(new Handler());
        this.mContext = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        try {
            ConnectivityManager
                    mConnectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            Method getMethod = mConnectivityManager.getClass().getMethod("getMobileDataEnabled");
            getMethod.setAccessible(true);
            boolean isEnabled = (Boolean) getMethod.invoke(mConnectivityManager);
            Log.i("stevenet", "mobile net:" + isEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        try {
            ConnectivityManager
                    mConnectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            Method getMethod = mConnectivityManager.getClass().getMethod("getMobileDataEnabled");
            getMethod.setAccessible(true);
            boolean isEnabled = (Boolean) getMethod.invoke(mConnectivityManager);
            Log.i("stevenet", "mobile net:" + isEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

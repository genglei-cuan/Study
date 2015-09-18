package steve.cn.mylib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Steve on 2015/7/27.
 */
public class DataTrafficTool {

    private Context mContext;
    private WifiManager mWifiManager = null;
    private ConnectivityManager mConnectivityManager = null;


    public DataTrafficTool(Context context) {
        this.mContext = context;
        mConnectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }


    //切换数据流量的开关
    public void switchMobileNet() {
        if (mConnectivityManager == null) {
            Log.i("steve", "ConnectivityManager is null");
            return;
        }
        try {
            // 判断是否有SIM卡
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm == null || TelephonyManager.SIM_STATE_UNKNOWN == tm.getSimState()
                    || tm.getNetworkOperatorName().equals("") || tm.getNetworkType() == 0) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Method getMethod = mConnectivityManager.getClass().getMethod("getMobileDataEnabled");
            getMethod.setAccessible(true);
            //获得当前的数据流量的状态
            boolean isEnabled = (Boolean) getMethod.invoke(mConnectivityManager);
            setMobileDataEnabled(!isEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置数据流量的状态 setMobileDataEnabled方法在5.0之后已经不可再访问
     *
     * @param state 开关状态
     */
    private void setMobileDataEnabled(boolean state) {
        try {
            final ConnectivityManager conman = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final Class conmanClass = Class.forName(conman.getClass().getName());
            final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            final Object iConnectivityManager = iConnectivityManagerField.get(conman);
            final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass()
                    .getName());
            final Method setMobileDataEnabledMethod;
            if (Build.VERSION.SDK_INT > 20) {
                setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod(
                        "setMobileDataEnabled", new Class[]{Boolean.TYPE});
            } else {
                setMobileDataEnabledMethod =
                        TelephonyManager.class.getDeclaredMethod("setDataEnabled", new Class[]{Boolean.TYPE});
            }

            setMobileDataEnabledMethod.setAccessible(true);
            if (Build.VERSION.SDK_INT > 20) {
                setMobileDataEnabledMethod
                        .invoke(iConnectivityManager, new Object[]{Boolean.valueOf(state)});
            } else {
                //目前这个权限似乎被谷歌收回了，声明权限也没用
                TelephonyManager telephonyManager =
                        (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                setMobileDataEnabledMethod.invoke(telephonyManager, new Object[]{Boolean.valueOf(state)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isWifiEnabled() {
        if (mWifiManager == null) {
            return false;
        }
        if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED
                || mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING
                || mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
            return true;
        }
        return false;
    }

    public boolean isMobileDataEnable() {
        try {
            Method getMethod = mConnectivityManager.getClass().getMethod("getMobileDataEnabled");
            getMethod.setAccessible(true);
            boolean isEnabled = (Boolean) getMethod.invoke(mConnectivityManager);
            return isEnabled;
        } catch (Exception e) {
            return false;
        }
    }
}

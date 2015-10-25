package cn.steve.datatraffic;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 监听网络状态 目前在数据流量在接通之后会被执行connected
 * <p/>
 * Created by Steve on 2015/7/28.
 */
public class MyPhoneStateListener extends PhoneStateListener {

    public void onDataConnectionStateChanged(int state) {
        switch (state) {
            case TelephonyManager.DATA_DISCONNECTED:
                Log.i("State: ", "Offline");
                break;
            case TelephonyManager.DATA_CONNECTING:
                Log.i("State: ", "CONNECTING");
                break;
            case TelephonyManager.DATA_CONNECTED:
                Log.i("State: ", "CONNECTED");
                break;
            case TelephonyManager.DATA_SUSPENDED:
                Log.i("State: ", "IDLE");
                break;
        }
    }
}

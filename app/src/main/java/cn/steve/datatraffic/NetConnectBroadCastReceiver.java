package cn.steve.datatraffic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import steve.cn.mylib.util.DataTrafficTool;


/**
 * Created by Steve on 2015/7/28.
 */
public class NetConnectBroadCastReceiver extends BroadcastReceiver {

    private Context mContext;
    private NetCallBack mCallback;
    private DataTrafficTool tool;

    public NetConnectBroadCastReceiver() {
        super();
    }

    public NetConnectBroadCastReceiver(Context context, NetCallBack callBack) {
        super();
        this.mCallback = callBack;
        this.mContext = context;
        tool = new DataTrafficTool(mContext);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
        } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            System.out.println("网络状态改变");
            //wifi连接上与否
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                System.out.println("wifi网络连接断开");
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                WifiManager
                        wifiManager =
                        (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //获取当前wifi名称
                System.out.println("连接到网络 " + wifiInfo.getSSID());
            }
        }
        //wifi打开与否
        else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int
                    wifistate =
                    intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                System.out.println("系统关闭wifi");
            } else if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                System.out.println("系统开启wifi");
            }
        } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager
                    cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                    System.out.println("CONNECTIVITY_ACTION");
                    if (ni.isConnected()) {
                        System.out.println("mobile is connected");
                    }
                } else if (ni.getTypeName().equals("WIFI")) {
                    if (ni.isConnected()) {
                        System.out.println("wifi is connected");
                    }
                }
            }
            NetworkInfo
                    activeNetworkInfo =
                    ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                            .getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    System.out.println("连接到网络 " + activeNetworkInfo.getDetailedState());
                }
            }
        }

    }
}

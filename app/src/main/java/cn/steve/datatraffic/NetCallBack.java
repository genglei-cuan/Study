package cn.steve.datatraffic;

/**
 * Created by Steve on 2015/7/28.
 */
public interface NetCallBack {

    void onWifiStateChanged(boolean isEnable);

    void onMobileNetChanged(boolean isEnable);

}

package cn.steve.datatraffic;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;
import steve.cn.mylib.util.DataTrafficTool;

/**
 * Created by Steve on 2015/7/28.
 */
public class DataTrafficActivity extends Activity implements NetCallBack {

    @Bind(R.id.buttonGetCurrentDataStatus)
    Button buttonGetStatus;
    @Bind(R.id.buttonSwitchStatus)
    Button buttonSwitch;
    @Bind(R.id.textViewResult)
    TextView textViewResult;


    private DataTrafficTool tool;
    private Context mContext;
    private NetCallBack mCallback;

    //网络广播接收者
    private BroadcastReceiver mSettingNetReceiver;
    private NetSettingObserver mContentObserver;
    private TelephonyManager myTelephonyManager;
    private PhoneStateListener callStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datatraffic);
        mContext = this;
        mCallback = this;
        ButterKnife.bind(this);
        tool = new DataTrafficTool(this);
        setObserver();
        mSettingNetReceiver = new NetConnectBroadCastReceiver(this.getApplicationContext(), this);
        myTelephonyManager =
                (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        callStateListener = new MyPhoneStateListener();
        myTelephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
        setLOLLIPOPListener();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setLOLLIPOPListener() {
        final ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        mConnectivityManager.addDefaultNetworkActiveListener(
                new ConnectivityManager.OnNetworkActiveListener() {
                    @Override
                    public void onNetworkActive() {
                        NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                        boolean isMobile = activeNetworkInfo.getTypeName().equals("MOBILE");
                        Log.i("steve LOLLIPOP:", "connection is active  " + isMobile);
                    }
                });
    }

    private void setObserver() {
        //注册观察者
        mContentObserver = new NetSettingObserver(this.getApplicationContext());
        if (Build.VERSION.SDK_INT > 16) {
            getApplicationContext().getContentResolver()
                    .registerContentObserver(Settings.Global.CONTENT_URI, true, mContentObserver);
        } else {
            getApplicationContext().getContentResolver()
                    .registerContentObserver(Settings.Secure.CONTENT_URI, true, mContentObserver);
        }
    }

    @OnClick(R.id.buttonSwitchStatus)
    public void switchStatus() {
        tool.switchMobileNet();
    }

    @OnClick(R.id.buttonGetCurrentDataStatus)
    public void getCurrentDataStatus() {
        textViewResult.setText(
                "wifi:" + tool.isWifiEnabled() + "   mobileData:" + tool.isMobileDataEnable());
    }

    @Override
    public void onWifiStateChanged(boolean isEnable) {
        Log.v("stevenet 广播", "wifi:" + isEnable);
    }

    @Override
    public void onMobileNetChanged(boolean isEnable) {
        Log.v("stevenet 广播", "mobileNet:" + isEnable);
    }


}

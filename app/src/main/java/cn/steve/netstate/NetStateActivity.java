package cn.steve.netstate;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import cn.steve.study.R;


/**
 * 判断网络的状态
 *
 * @author Steve
 */
public class NetStateActivity extends Activity implements OnClickListener {

    Resources res = null;
    private ConnectivityManager mConnectivityManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        mConnectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        findViewById(R.id.buttonMain).setOnClickListener(this);
        res = this.getResources();
        int id = res.getIdentifier("button_net", "id", getPackageName());
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, String.valueOf(isMobileNetOn()), Toast.LENGTH_SHORT).show();
        ;
    }

    public boolean isMobileNetOn() {
        State state = null;
        state = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (state == State.CONNECTED || state == State.DISCONNECTING
                || state == State.CONNECTING) {
            return true;
        }
        return false;
    }

    /**
     * 检测网络是否连接
     */
    private boolean checkWifiIsConnected() {
        boolean flag = false;
        // 去进行判断网络是否连接
        if (mConnectivityManager.getActiveNetworkInfo() != null) {
            flag = mConnectivityManager.getActiveNetworkInfo().isAvailable();
        }
        // 网络通畅
        if (flag) {
            State wifi =
                    mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
                flag = true;
            }
        }
        return flag;
    }
}

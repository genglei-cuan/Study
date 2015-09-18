package cn.steve.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.steve.Utils.SteveLog;

/**
 * 正常的广播接收者
 *
 * @author Steve
 */

public class NormalBCReceiverB extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String flag_str = intent.getStringExtra("flag");

        SteveLog.log("NormalBCReceiverB" + flag_str);
    }

}

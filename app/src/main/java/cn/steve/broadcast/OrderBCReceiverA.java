package cn.steve.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import steve.cn.mylib.log.SteveLog;

public class OrderBCReceiverA extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("flag");
        SteveLog.log("OrderBCReceiverA进行响应-->" + s);
        SteveLog.log("A接收到的消息" + getResultData());
        setResultData("A发出的消息");
    }

}

package cn.steve.broadcast;

import cn.steve.Utils.SteveLog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 正常的广播接收者
 * 
 * 1.广播接收者，的生命周期在onReceive执行完之后，就结束了。 2.异步广播接收者的接收顺序是异步的，不知道哪个会接收，但是可以设置优先级 3.如果需要有顺序的接收，就需要借助有序广播
 * 
 * @author Steve
 *
 */

public class NormalBCReceiverA extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    String flag_str = intent.getStringExtra("flag");

    SteveLog.log("NormalBCReceiverA" + flag_str);
  }

}

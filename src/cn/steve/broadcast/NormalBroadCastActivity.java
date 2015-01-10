package cn.steve.broadcast;

import cn.steve.study.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * 发送端activity
 * 
 * @author Steve
 *
 */
public class NormalBroadCastActivity extends Activity {
  boolean isRegistered = false;
  private Button mButton_send;
  private Button mButton_register;
  private Button mButton_unregister;
  BroadcastReceiver normalBCReceiverA = new NormalBCReceiverA();
  BroadcastReceiver normalBCReceiverB = new NormalBCReceiverA();

  public static final String ACTION_NORMAL_BC = "cn.steve.broadcast.normalBC.VIEW";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_broadcast_normal);
    mButton_send = (Button) findViewById(R.id.broadcast_send);
    mButton_register = (Button) findViewById(R.id.broadcast_register);
    mButton_unregister = (Button) findViewById(R.id.broadcast_unregister);

    mButton_send.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ACTION_NORMAL_BC);
        intent.putExtra("flag", "SimpleBroadCastActivity");
        sendBroadcast(intent); // 基本函数
      }
    });

    mButton_register.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        // 动态注册方式
        NormalBroadCastActivity.this.setBCReceiver();
      }
    });

    mButton_unregister.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        if (isRegistered) {
          unregisterReceiver(normalBCReceiverA);
          unregisterReceiver(normalBCReceiverB);
          isRegistered = false;
        }

      }
    });

  }

  // 注册接收者
  private void setBCReceiver() {
    IntentFilter intentFilter = new IntentFilter(ACTION_NORMAL_BC);
    registerReceiver(normalBCReceiverA, intentFilter);
    IntentFilter intentFilter2 = new IntentFilter(ACTION_NORMAL_BC);
    registerReceiver(normalBCReceiverB, intentFilter2);
    isRegistered = true;
  }


}

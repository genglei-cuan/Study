
package cn.steve.broadcast;

import cn.steve.study.R;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 发送有序的广播
 * 
 * @author Steve
 */
public class OrderBroadCastActivity extends Activity {
    public static final String ACTION_ORDER_BC = "cn.steve.broadcast.orderBC.VIEW";

    private Button mButton_send;
    private Button mButton_register;
    private Button mButton_unregister;

    private OrderBCReceiverA mOrderBCReceiverA = new OrderBCReceiverA();
    private OrderBCReceiverB mOrderBCReceiverB = new OrderBCReceiverB();

    boolean isRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_order);
        mButton_send = (Button) findViewById(R.id.broadcast_send);
        mButton_register = (Button) findViewById(R.id.broadcast_register);
        mButton_unregister = (Button) findViewById(R.id.broadcast_unregister);

        mButton_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_ORDER_BC);
                intent.putExtra("flag", "SimpleBroadCastActivity");
                sendOrderedBroadcast(intent, null);
            }
        });

        mButton_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 动态注册方式
                setBCReceiver();

            }
        });

        mButton_unregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRegistered) {
                    // 取消注册
                    unregisterReceiver(mOrderBCReceiverA);
                    unregisterReceiver(mOrderBCReceiverB);
                    isRegistered = false;
                }
            }
        });

    }

    // 注册接收者
    private void setBCReceiver() {
        IntentFilter intentFilter1 = new IntentFilter(ACTION_ORDER_BC);
        registerReceiver(mOrderBCReceiverA, intentFilter1);
        IntentFilter intentFilter2 = new IntentFilter(ACTION_ORDER_BC);
        registerReceiver(mOrderBCReceiverB, intentFilter2);
        isRegistered = true;
    }
}

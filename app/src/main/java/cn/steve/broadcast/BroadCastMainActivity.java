package cn.steve.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import cn.steve.study.R;

/**
 * 研究的是发送广播
 *
 * @author Steve
 */
public class BroadCastMainActivity extends Activity {

    private Button mButton_simple;
    private Button mButton_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_broadcast_main);
        mButton_simple = (Button) findViewById(R.id.button_simple);
        mButton_order = (Button) findViewById(R.id.button_order_broadcast);

        mButton_simple.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                    new Intent(BroadCastMainActivity.this, NormalBroadCastActivity.class));
            }
        });

        mButton_order.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BroadCastMainActivity.this, OrderBroadCastActivity.class));

            }
        });
    }
}

package cn.steve.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * handler post一个runnable
 *
 * @author Steve
 */
public class HandlerActivity extends Activity {

    private TextView textView;
    private ImageView imageView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = (TextView) findViewById(R.id.textView1);

        new Thread() {
            public void run() {
                try {
                    System.out.println("first" + Thread.currentThread());
                    Thread.sleep(1000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("second" + Thread.currentThread());
                            textView.setText("upate");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }
}

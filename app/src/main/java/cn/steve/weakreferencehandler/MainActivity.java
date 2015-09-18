
package cn.steve.weakreferencehandler;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.steve.study.R;

/**
 * 为了防止内存泄露 将handler设置成static 然后对activity的引用采用弱引用
 * 
 * @author Steve
 */
public class MainActivity extends Activity {
    public static final int DIALOG_SHOW = 2;
    public static final int DIALOG_DISMISS = 3;

    Button mButton = null;
    ProgressDialog mProgressDialog = null;
    MyHandler mHandler = null;

    static class MyHandler extends Handler {
        private WeakReference<MainActivity> mOuter;

        public MyHandler(MainActivity activity) {
            // 弱引用
            mOuter = new WeakReference<MainActivity>(activity);
        }

        public void handleMessage(Message msg) {
            MainActivity mq = mOuter.get();
            switch (msg.what) {
                case DIALOG_SHOW:
                    if (mq.isFinishing()) {
                        break;
                    }
                    mq.mProgressDialog = new ProgressDialog(mq);
                    mq.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mq.mProgressDialog.setTitle("提示");
                    mq.mProgressDialog.setIcon(R.drawable.customer_icon);
                    mq.mProgressDialog.setCancelable(false);
                    mq.mProgressDialog.show();
                    break;
                case DIALOG_DISMISS:
                    mq.mProgressDialog.cancel();
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        mHandler = new MyHandler(MainActivity.this);
        findviews();
    }

    private void findviews() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.mHandler.obtainMessage(DIALOG_SHOW).sendToTarget();
            }
        });
    }
}

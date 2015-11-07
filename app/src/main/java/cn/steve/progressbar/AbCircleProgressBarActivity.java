package cn.steve.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import cn.steve.study.R;


/**
 * Created by Steve on 2015/9/11.
 */
public class AbCircleProgressBarActivity extends Activity {

    private String TAG = "MainActivity";
    // ProgressBar进度控制
    private AbCircleProgressBar mAbProgressBar;
    // 最大100
    private int max = 100;
    private int progress = 0;
    private TextView numberText, maxText;
    private Handler mUpdateHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startAddProgress();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Runnable mUpdateRunnable = new Runnable() {
        public void run() {
            while (true) {
                Message message = new Message();
                message.what = 1;
                mUpdateHandler.sendMessage(message);
                try {
                    // 更新间隔毫秒数
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar_circle);

        // ProgressBar进度控制
        // ProgressBar进度控制
        mAbProgressBar = (AbCircleProgressBar) findViewById(R.id.circleProgressBar);

        numberText = (TextView) findViewById(R.id.numberText);
        maxText = (TextView) findViewById(R.id.maxText);

        maxText.setText("总共  " + String.valueOf(max));
        mAbProgressBar.setMax(max);
        mAbProgressBar.setProgress(progress);

        startAddProgress();

        mAbProgressBar.setAbOnProgressListener(new AbCircleProgressBar.AbOnProgressListener() {

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onComplete() {
                progress = 0;
                mAbProgressBar.reset();
            }
        });

    }

    public void startAddProgress() {
        progress = progress + 10;
        numberText.setText(String.valueOf(progress));
        mAbProgressBar.setProgress(progress);
        mUpdateHandler.sendEmptyMessageDelayed(1, 1000);
    }

}

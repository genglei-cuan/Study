package cn.steve.progressbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.steve.study.R;


/**
 * 带有数字的进度条
 *
 * @author Steve
 */
public class ProgressMainActivity extends Activity {

    private int counter = 0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        final NumberProgressBar bnp1 = (NumberProgressBar) findViewById(R.id.numberbar1);
        final NumberProgressBar bnp2 = (NumberProgressBar) findViewById(R.id.numberbar2);

        bnp2.setProgress(50);
        bnp2.setReachedBarColor(Color.rgb(66, 145, 241));

        counter = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp1.incrementProgressBy(1);
                        counter++;
                        if (counter == 110) {
                            bnp1.setProgress(0);
                            counter = 0;

                        }
                    }
                });
            }
        }, 1000, 100);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}

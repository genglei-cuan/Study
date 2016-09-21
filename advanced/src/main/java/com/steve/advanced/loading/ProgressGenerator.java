package com.steve.advanced.loading;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;

import java.util.Random;

public class ProgressGenerator {

    private OnCompleteListener mListener;
    private int mProgress;
    private Random random = new Random();

    public ProgressGenerator(OnCompleteListener listener) {
        mListener = listener;
    }

    public void start(final ProcessButton button) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100) {
                    handler.postDelayed(this, generateDelay());
                } else {
                    mListener.onComplete();
                    mProgress = 0;
                }
            }
        }, generateDelay());
    }

    private int generateDelay() {
        return random.nextInt(1000);
    }

    public interface OnCompleteListener {

        void onComplete();
    }
}

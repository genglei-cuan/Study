package cn.steve.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/9/6.
 */
public class LifeActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_imageview);
        System.out.println("onCreate--B");
        LifeUtils.activity = this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState--B");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("onRestoreInstanceState--B");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause--B");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop--B");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart--B");
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume--B");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart--B");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy--B");
    }
}
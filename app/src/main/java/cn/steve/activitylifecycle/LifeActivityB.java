package cn.steve.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Steve on 2015/9/6.
 */
public class LifeActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate--B");
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

}
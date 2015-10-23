package cn.steve.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.steve.study.R;


/**
 * Created by Steve on 2015/9/6.
 */
public class LifeActivityA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        findViewById(R.id.buttonMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeActivityA.this, LifeActivityB.class));
            }
        });
        System.out.println("onCreate--A");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState--A");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("onRestoreInstanceState--A");

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause--A");

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart--A");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume--A");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop--A");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart--A");

    }

}
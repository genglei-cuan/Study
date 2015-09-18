package cn.steve.contextmean;

import android.app.Activity;
import android.os.Bundle;

import cn.steve.study.R;

public class ContextActivity extends Activity {

    private MyTextView tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextmean);
        tv = (MyTextView) findViewById(R.id.context_tv);
        System.out.println(tv.getContext());

    }

}

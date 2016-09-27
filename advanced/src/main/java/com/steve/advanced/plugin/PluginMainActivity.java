package com.steve.advanced.plugin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import com.steve.advanced.R;

/**
 * Created by steveyan on 16-9-27.
 */

public class PluginMainActivity extends AppCompatActivity {

    private AppCompatButton button;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_text);
        assignViews();
    }


    private void assignViews() {
        button = (AppCompatButton) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
    }


}

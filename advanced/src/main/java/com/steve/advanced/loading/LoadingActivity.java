package com.steve.advanced.loading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.steve.advanced.R;

public class LoadingActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener {

    private LinearLayout bottom;
    private ActionProcessButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        bottom = (LinearLayout) findViewById(R.id.bottom);
        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoad(progressGenerator);
            }
        });
        findViewById(R.id.loadingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoad(progressGenerator);
            }
        });
    }

    private void startLoad(ProgressGenerator progressGenerator) {
        btnSignIn.setVisibility(View.VISIBLE);
        bottom.setVisibility(View.GONE);
        progressGenerator.start(btnSignIn);
        btnSignIn.setEnabled(false);
    }

    @Override
    public void onComplete() {
        bottom.setVisibility(View.VISIBLE);
        //btnSignIn.setProgress(-1);
        //btnSignIn.setEnabled(true);
        btnSignIn.setVisibility(View.GONE);
        Toast.makeText(this, "Complete", Toast.LENGTH_LONG).show();
    }
}

package com.steve.advanced.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.steve.advanced.R;

public class OkHttpActivity extends AppCompatActivity {


    private static final String GETURL = "https://raw.github.com/square/okhttp/master/README.md";
    private AppCompatButton button;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_text);
        assignViews();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    request(i + "");
                }

            }
        });
    }

    private void assignViews() {
        button = (AppCompatButton) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
    }

    private void request(final String num) {
        OkHttpProvider.getInstance().get(GETURL, "key", new MyHttpCallBack() {
            @Override
            public void onFailure() {
                System.out.println(num + "error");
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String s = textView.getText().toString();
                        textView.setText(s + "\n" + num);
                    }
                });

            }
        });
    }

}

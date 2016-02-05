package cn.steve.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import cn.steve.study.R;


public class TranslucentBarColorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_color_translucent_bar);
        findViewById(R.id.btn_show_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_show_toast) {
            Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show();
        }
    }
}

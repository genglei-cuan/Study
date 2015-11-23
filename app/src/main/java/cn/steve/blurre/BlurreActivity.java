package cn.steve.blurre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/5.
 */
public class BlurreActivity extends AppCompatActivity {

    private android.widget.TextView textViewMain;
    private RelativeLayout rlBlurreRoot;
    private LinearLayout llBlurreRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurre);
        this.textViewMain = (TextView) findViewById(R.id.textViewBlurre);
        this.llBlurreRoot = (LinearLayout) findViewById(R.id.llBlurreRoot);
        rlBlurreRoot = (RelativeLayout) findViewById(R.id.rlBlurreRoot);
        BlurUtil.applyBlur(rlBlurreRoot, textViewMain, this);
        BlurUtil.applyBlur(rlBlurreRoot, llBlurreRoot, this);

    }

}

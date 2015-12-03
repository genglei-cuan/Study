package cn.steve.blurre.renderscript;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import cn.steve.study.R;
import steve.cn.mylib.util.BlurUtil;

/**
 * Created by yantinggeng on 2015/12/3.
 */
public class RenderScriptMainActivity extends AppCompatActivity {

    private android.widget.ImageView renderScriptImageView;
    private android.widget.SeekBar renderScriptSeekBar;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renderscript);
        this.renderScriptSeekBar = (SeekBar) findViewById(R.id.renderScriptSeekBar);
        this.renderScriptImageView = (ImageView) findViewById(R.id.renderScriptImageView);

        mBitmap = ((BitmapDrawable) renderScriptImageView.getDrawable()).getBitmap();

        renderScriptSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 0) {
                    return;
                }
                renderScriptImageView.setImageBitmap(BlurUtil.getBlurBitmap(RenderScriptMainActivity.this,mBitmap,progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}

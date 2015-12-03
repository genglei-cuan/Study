package cn.steve.blurre.renderscript;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.support.v8.renderscript.ScriptIntrinsicColorMatrix;
import android.widget.ImageView;
import android.widget.SeekBar;

import cn.steve.study.R;

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
                renderScriptImageView.setImageBitmap(getBlurBitmap(mBitmap, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Bitmap getBlurBitmap(Bitmap inputBitmap, float radius) {
        //资源
        Bitmap outBitmap = inputBitmap.copy(inputBitmap.getConfig(), true);
        Bitmap grayBitmap = inputBitmap.copy(inputBitmap.getConfig(), true);
        //创建context 和 I/O allocations
        final RenderScript rs = RenderScript.create(this);
        final Allocation input =Allocation.createFromBitmap(rs, inputBitmap, Allocation.MipmapControl.MIPMAP_NONE,Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        //Blur the image
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(outBitmap);
        //Make the image greyscale
        final ScriptIntrinsicColorMatrix scriptColor =ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        scriptColor.setGreyscale();
        scriptColor.forEach(input, output);
        output.copyTo(grayBitmap);

        //We don't need RenderScript anymore
        rs.destroy();
        return outBitmap;
    }
}

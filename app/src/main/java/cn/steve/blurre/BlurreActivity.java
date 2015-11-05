package cn.steve.blurre;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/5.
 */
public class BlurreActivity extends AppCompatActivity {

    private android.widget.TextView textViewMain;
    private RelativeLayout rlBlurreRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurre);
        this.textViewMain = (TextView) findViewById(R.id.textViewBlurre);
        rlBlurreRoot = (RelativeLayout) findViewById(R.id.rlBlurreRoot);
        applyBlur(rlBlurreRoot, textViewMain);
    }

    private void applyBlur(final View backView, final View destinationView) {
        backView.getViewTreeObserver()
            .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    backView.getViewTreeObserver().removeOnPreDrawListener(this);
                    backView.buildDrawingCache();
                    Bitmap bmp = backView.getDrawingCache();
                    blur(bmp, destinationView);
                    return true;
                }
            });
    }

    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 1;
        float radius = 20;
        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
                                             (int) (view.getMeasuredHeight() / scaleFactor),
                                             Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);
        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackgroundDrawable(new BitmapDrawable(getResources(), overlay));
    }
}

package cn.steve.blurre;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by yantinggeng on 2015/11/6.
 */
public class BlurUtil {

    public static void applyBlur(final View backView, final View destinationView,
                                 final Context context) {
        backView.getViewTreeObserver()
            .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    backView.getViewTreeObserver().removeOnPreDrawListener(this);
                    backView.buildDrawingCache();
                    Bitmap bmp = backView.getDrawingCache();
                    blur(bmp, destinationView, context);
                    return true;
                }
            });
    }

    private static void blur(Bitmap bkg, View view, Context context) {
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
        view.setBackgroundDrawable(new BitmapDrawable(context.getResources(), overlay));
    }
}

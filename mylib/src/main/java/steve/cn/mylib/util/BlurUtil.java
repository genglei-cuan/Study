package steve.cn.mylib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.support.v8.renderscript.ScriptIntrinsicColorMatrix;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by yantinggeng on 2015/11/6.
 */
public class BlurUtil {


    /**
     * 指定位置进行模糊
     * @param backView 背景view
     * @param destinationView 目标需要被模糊的部分
     * @param context 上下文
     */
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
        //模糊的半径
        float radius = 10;
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

    /**
     * 对图片整体模糊，返回一个模糊的图片
     * @param context 上下文
     * @param inputBitmap 需要模糊的原图
     * @param radius 模糊的半径，值越大，则越模糊在0<radius<=25
     * @return
     */
    public static Bitmap getBlurBitmap(final Context context,Bitmap inputBitmap, float radius) {
        //资源
        Bitmap outBitmap = inputBitmap.copy(inputBitmap.getConfig(), true);
        Bitmap grayBitmap = inputBitmap.copy(inputBitmap.getConfig(), true);
        //创建context 和 I/O allocations
        final RenderScript rs = RenderScript.create(context);
        final Allocation
            input =Allocation.createFromBitmap(rs, inputBitmap, Allocation.MipmapControl.MIPMAP_NONE,Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        //Blur the image
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(outBitmap);
        //Make the image greyscale
        final ScriptIntrinsicColorMatrix
            scriptColor =ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        scriptColor.setGreyscale();
        scriptColor.forEach(input, output);
        output.copyTo(grayBitmap);

        //We don't need RenderScript anymore
        rs.destroy();
        return outBitmap;
    }
}

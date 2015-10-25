package cn.steve.floatingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Steve on 2015/9/1.
 */
public class Arrow extends View {

    Drawable drawable;
    private Paint paint;
    private int mWidth;
    private int mHeight;
    private Path path1;
    private Path path2;

    public Arrow(Context context) {
        super(context);
        init();
    }

    public Arrow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
    }

    private void drawBackground(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.FILL);
        RectF oval = new RectF();                       //RectF对象
        oval.left = 100;                                //左边
        oval.top = 400;                                 //上边
        oval.right = 400;                               //右边
        oval.bottom = 700;                               //下边
        canvas.drawArc(oval, 90, 180, true, paint);    //绘制圆弧
    }
}

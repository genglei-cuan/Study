package cn.steve.circle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import cn.steve.study.R;

public class SleepClockCircle extends View {

    int circleX;// 圆心X坐标
    int r;// 圆半径
    RectF rectF;
    // 刻度的长度
    int timeLineLength = 15;
    // 刻度的宽度
    int timeLineWidth = 4;
    // 外圈进度渐变的宽度
    int progressWidth = 10;
    int width, height;
    Context context;
    private Paint mPaint;// 画笔
    private int bordColor;// 边框颜色
    private int timeColor;// 刻度颜色
    private int bordWidth;// 边框宽度
    private float startAngle = 0;
    private float endAngle = 0;

    public SleepClockCircle(Context context) {
        this(context, null);
    }

    public SleepClockCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SleepClockCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        // 加载并获得自定义属性
        TypedArray a =
            context.getTheme().obtainStyledAttributes(attrs, R.styleable.SleepClockCircle,
                                                      defStyleAttr, 0);
        // 获得自定义属性个数
        int n = a.getIndexCount();

        // 循环获得我们在布局文件中给自定义属性赋的值
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SleepClockCircle_bordColor:
                    bordColor = a.getColor(attr, Color.parseColor("#FF0011"));
                    break;
                case R.styleable.SleepClockCircle_timeColor:
                    timeColor = a.getColor(attr, Color.parseColor("#FF0000"));
                    break;
                case R.styleable.SleepClockCircle_bordWidth:
                    bordWidth =
                        a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 8, getResources().getDisplayMetrics()));
                    break;
            }

        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心


    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        circleX = width / 2;// 圆心坐标x
        r = circleX - bordWidth - progressWidth;// 半径

        mPaint.setShader(null);
        // 画钟表边框
        mPaint.setStrokeWidth(bordWidth); // 设置圆环的宽度
        mPaint.setColor(bordColor);
        canvas.drawCircle(circleX, circleX, r, mPaint);

        // 画刻度
        // 12
        mPaint.setStrokeWidth(timeLineWidth);
        mPaint.setColor(timeColor);
        canvas.drawLine(circleX, bordWidth + progressWidth, circleX, bordWidth + progressWidth
                                                                     + timeLineLength, mPaint);
        // 1
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX + r * Math.cos(Math.toRadians(60))), (float) (circleX - r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             30))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        mPaint);
        // 2
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX + r * Math.cos(Math.toRadians(30))), (float) (circleX - r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             60))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        mPaint);
        // 3
        mPaint.setStrokeWidth(timeLineWidth);
        canvas
            .drawLine(width - bordWidth - progressWidth, circleX, width - bordWidth - progressWidth
                                                                  - timeLineLength, circleX,
                      mPaint);
        // 4
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX + r * Math.cos(Math.toRadians(30))), (float) (circleX + r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             60))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        mPaint);
        // 5
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX + r * Math.cos(Math.toRadians(60))), (float) (circleX + r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             30))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        mPaint);
        // 6
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine(circleX, width - bordWidth - progressWidth, circleX, width - bordWidth
                                                                             - progressWidth
                                                                             - timeLineLength,
                        mPaint);
        // 7
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX - r * Math.cos(Math.toRadians(60))), (float) (circleX + r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             30))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        mPaint);
        // 8
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX - r * Math.cos(Math.toRadians(30))), (float) (circleX + r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             60))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        (float) (circleX + (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        mPaint);
        // 9
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine(bordWidth + progressWidth, circleX,
                        bordWidth + progressWidth + timeLineLength,
                        circleX, mPaint);
        // 10
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX - r * Math.cos(Math.toRadians(30))), (float) (circleX - r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             60))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        mPaint);
        // 11
        mPaint.setStrokeWidth(timeLineWidth);
        canvas.drawLine((float) (circleX - r * Math.cos(Math.toRadians(60))), (float) (circleX - r
                                                                                                 * Math
                                                                                                     .cos(
                                                                                                         Math.toRadians(
                                                                                                             30))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(60))),
                        (float) (circleX - (r - timeLineLength) * Math.cos(Math.toRadians(30))),
                        mPaint);

        // 画外圈的色彩变化
        LinearGradient lg =
            new LinearGradient(0, 0, getWidth(), getHeight(), new int[]{Color.parseColor("#3a708d"),
                                                                        Color
                                                                            .parseColor("#1cd98d")},
                               null, Shader.TileMode.REPEAT);

        // 为Paint设置渐变器
        mPaint.setShader(lg);

        mPaint.setStrokeWidth(progressWidth);
        // canvas.drawCircle(circleX, circleX, r + bordWidth, mPaint);

        // 绘制弧线区域
        RectF rect =
            new RectF(circleX - r - progressWidth, circleX - r - progressWidth, circleX + r
                                                                                + progressWidth,
                      circleX + r + progressWidth);
        canvas.drawArc(rect, startAngle, endAngle, false, mPaint);

    }


    public void setStartEndAngle(float start, float end) {
        this.startAngle = start - 90;
        this.endAngle = end;
        invalidate();
    }


}

package cn.steve.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cn.steve.study.R;

/**
 * 这里只到二次贝塞尔曲线，再往上不会了
 * 
 * @author steve.yan
 *
 */
public class CustomerPaintView extends View {
  private Resources myResources;

  // 画笔，定义绘制属性
  private Paint myPaint;
  private Paint mBitmapPaint;

  // 绘制路径
  private Path myPath;

  // 画布及其底层位图
  private Bitmap myBitmap;
  private Canvas myCanvas;

  private float mX, mY;
  private static final float TOUCH_TOLERANCE = 4;

  // 记录宽度和高度
  private int mWidth;
  private int mHeight;

  public CustomerPaintView(Context context) {
    super(context);
    initialize();
  }

  public CustomerPaintView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
  }

  public CustomerPaintView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  /**
   * 初始化工作
   */
  private void initialize() {
    // Get a reference to our resource table.
    myResources = getResources();
    // 绘制自由曲线用的画笔
    myPaint = new Paint();
    myPaint.setAntiAlias(true);
    myPaint.setDither(true);
    // 设置画笔的颜色
    myPaint.setColor(myResources.getColor(R.color.purple_dark));
    myPaint.setStyle(Paint.Style.STROKE);
    myPaint.setStrokeJoin(Paint.Join.ROUND);
    myPaint.setStrokeCap(Paint.Cap.ROUND);
    myPaint.setStrokeWidth(12);
    // 设置画笔的类型
    // myPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    // myPaint.setMaskFilter(new EmbossMaskFilter(new float[] { 1, 1, 1 },0.4f, 6,3.5f));
    // myPaint.setAlpha(50);
    myPath = new Path();
    mBitmapPaint = new Paint(Paint.DITHER_FLAG);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
    mHeight = h;
    myBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    myCanvas = new Canvas(myBitmap);
  }

  // 将画布canvas作为图片进行保存
  public Bitmap save_img() {
    myCanvas.save(Canvas.ALL_SAVE_FLAG);
    myCanvas.restore();
    return myBitmap;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        touch_start(x, y);
        invalidate();
        break;
      case MotionEvent.ACTION_MOVE:
        touch_move(x, y);
        invalidate();
        break;
      case MotionEvent.ACTION_UP:
        touch_up();
        invalidate();
        break;
    }
    return true;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    // 背景颜色
    canvas.drawColor(getResources().getColor(R.color.blue_dark));
    // 如果不调用这个方法，绘制结束后画布将清空
    canvas.drawBitmap(myBitmap, 0, 0, mBitmapPaint);
    // 绘制路径
    canvas.drawPath(myPath, myPaint);
  }

  private void touch_start(float x, float y) {
    myPath.reset();
    myPath.moveTo(x, y);
    mX = x;
    mY = y;
  }

  private void touch_move(float x, float y) {
    float dx = Math.abs(x - mX);
    float dy = Math.abs(y - mY);
    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {

      /**
       * 第一个参数：控制点的x坐标
       * 
       * 第二个参数：控制点的y坐标
       * 
       * 第三个参数：结束点的x坐标
       * 
       * 第四个参数：结束点的y坐标
       * 
       * 二次贝塞尔曲线
       * 
       */

      myPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);

      /**
       * 
       * @param x1 第一个控制点的X坐标
       * @param y1 第一个控制点的Y坐标
       * @param x2 第二个控制点的X坐标
       * @param y2 第二个控制点的Y坐标
       * @param x3 结束点的X坐标
       * @param y3 结束点的Y坐标
       * 
       */
      // myPath.cubicTo(mX, mY, (x + dx) , (y + dy), (x+mX)/2, (y+mY)/2);

      mX = x;
      mY = y;

    }
  }

  private void touch_up() {
    myPath.lineTo(mX, mY);
    // commit the path to our offscreen
    // 如果少了这一句，笔触抬起时myPath重置，那么绘制的线将消失
    myCanvas.drawPath(myPath, myPaint);
    // kill this so we don't double draw
    myPath.reset();
  }

  /**
   * 清除整个图像
   */
  public void clear() {
    // 清除方法1：重新生成位图
    // myBitmap = Bitmap.createBitmap(mWidth, mHeight,
    // Bitmap.Config.ARGB_8888);
    // myCanvas = new Canvas(myBitmap);
    // 清除方法2：将位图清除为白色
    myBitmap.eraseColor(myResources.getColor(R.color.white));
    // 两种清除方法都必须加上后面这两步：
    // 路径重置
    myPath.reset();
    // 刷新绘制
    invalidate();
  }
}

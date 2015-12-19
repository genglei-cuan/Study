package cn.steve.viewcoordinate;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/17.
 */
public class ObtainWidthHeightActivity extends AppCompatActivity {

    private static final String TAG = "WidthHeightActivity";

    private android.widget.TextView textViewMain;
    private android.widget.LinearLayout linearLayoutRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.linearLayoutRoot = (LinearLayout) findViewById(R.id.linearLayoutRoot);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        getWidthHeight1();
        getWidthHeight2();
        getWidthHeight3();
    }


    /**
     * 这种方法适用于需要在onCreate完成之前就获得一个view的宽和高的情况。
     * <p/>
     * 优点：可以立即获得宽和高
     * <p/>
     * 缺点：人为的多了一次测量过程
     * <p/>
     * 这种方法的原理是直接调用一个view或者viewgroup的measure方法去测量，测量之后该view的getMeasuredHeight()就会返回刚才测量所得的高，getMeasuredWidth返回测量所得宽。本来在布局加载的过程中，view的measure方法一定会被系统调用，但这发生在我们所不知道的某个时间点，为了在这之前提前得到测量结果，我们主动调用measure方法，但是这样做的好处是可以立即获得宽和高，坏处是多了一次测量过程。
     * 至于为什么参数是LayoutParams.WRAP_CONTENT，那是因为我假设这个view的layout_width和layout_height为wrap_content,因为如果为一个确切的值，还有必要测量吗？
     */
    private void getWidthHeight1() {

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

//        textViewMain.measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewMain.measure(w, h);
        int height = textViewMain.getMeasuredHeight();
        int width = textViewMain.getMeasuredWidth();

        Log.i(TAG, "getWidthHeight1: [height:" + height + "] width:[" + width + "]");
    }

    /**
     * 这个方法，我们需要注册一个ViewTreeObserver的监听回调，这个监听回调，就是专门监听绘图的，既然是监听绘图，那么我们自然可以获取测量值了，
     * 同时，我们在每次监听前remove前一次的监听，避免重复监听。
     */
    private void getWidthHeight2() {
        final ViewTreeObserver vto = textViewMain.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                int height = textViewMain.getMeasuredHeight();
                int width = textViewMain.getMeasuredWidth();
                textViewMain.getViewTreeObserver().removeOnPreDrawListener(this);
                Log.i(TAG, "getWidthHeight1: [height:" + height + "] width:[" + width + "]");
                return true;
            }
        });
    }

    /**
     * 布局监听类ViewTreeObserver的OnGlobalLayoutListener
     * <p/>
     * 当一个view的布局加载完成或者布局发生改变时OnGlobalLayoutListener可以监听到，利用这点我们可以在布局加载完成的瞬间获得一个view的宽高。
     * OnGlobalLayoutListener的onGlobalLayout被回调之前是没有值的。由于布局状态可能会发生多次改变，因此OnGlobalLayoutListener的onGlobalLayout可能被回调多次，所以我们在
     * 第一次获得值之后就将listener注销掉。
     */
    private void getWidthHeight3() {
        ViewTreeObserver vto = textViewMain.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = textViewMain.getHeight();
                int width = textViewMain.getWidth();
                textViewMain.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Log.i(TAG, "getWidthHeight1: [height:" + height + "] width:[" + width + "]");
            }
        });
    }


/////////////////////////////////////////////////////////////////////////////////以下方法相对总结过的。


    /**
     * 对于焦点状态发生改变的时候，这个方法会被回调，
     * 当获取到焦点的时候，表明这个时候是可以获取到view的宽高的。
     * <p/>
     * 这个方法的隐含问题是，在pause和resume的过程中，均会被调用，会被多次调用
     *
     * @param hasFocus 是否获取到了焦点
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int width = textViewMain.getMeasuredWidth();
            int height = textViewMain.getMeasuredHeight();
            Log.d(TAG, "onWindowFocusChanged() called : " + "width = [" + width + "]" + "height = [" + height + "]");
        }
    }


    /**
     * 通过post runnable的方式，将runnable推送到消息队列的尾部，等待Looper调用的时候，表示view已经初始化好了
     */
    private void method4() {
        textViewMain.post(new Runnable() {
            @Override
            public void run() {
                int width = textViewMain.getMeasuredWidth();
                int height = textViewMain.getMeasuredHeight();
                Log.d(TAG, "Runnable() called : " + "width = [" + width + "]" + "height = [" + height + "]");
            }
        });
    }


    /**
     * 通过布局状态变化的观察者
     */
    private void method5() {
        ViewTreeObserver observer = textViewMain.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                textViewMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = textViewMain.getMeasuredWidth();
                int height = textViewMain.getMeasuredHeight();
                Log.d(TAG, "OnGlobalLayoutListener called : " + "width = [" + width + "]" + "height = [" + height + "]");
            }
        });
    }


    /**
     * 手动主动去测量
     *
     * @param exactWidth  精确值
     * @param exactHeight 精确值
     */
    private void method6(int exactWidth, int exactHeight) {
        int w = exactWidth;
        int h = exactHeight;
        int widthMeasureSpec;
        int heightMeasureSpec;
        //精确值
        if (exactHeight > 0) {
            w = exactWidth;
            h = exactHeight;
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY);
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY);
        } else {
            //wrap_content
            w = (1 << 30) - 1;
            h = (1 << 30) - 1;
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.AT_MOST);
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.AT_MOST);
        }

        textViewMain.measure(widthMeasureSpec, heightMeasureSpec);
        int width = textViewMain.getMeasuredWidth();
        int height = textViewMain.getMeasuredHeight();
        Log.d(TAG, "method6 called : " + "width = [" + width + "]" + "height = [" + height + "]");

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
}

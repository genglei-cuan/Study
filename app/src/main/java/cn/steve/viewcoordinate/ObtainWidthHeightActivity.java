package cn.steve.viewcoordinate;

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
     *
     * 优点：可以立即获得宽和高
     *
     * 缺点：人为的多了一次测量过程
     *
     * 这种方法的原理是直接调用一个view或者viewgroup的measure方法去测量，测量之后该view的getMeasuredHeight()就会返回刚才测量所得的高，getMeasuredWidth返回测量所得宽。本来在布局加载的过程中，view的measure方法一定会被系统调用，但这发生在我们所不知道的某个时间点，为了在这之前提前得到测量结果，我们主动调用measure方法，但是这样做的好处是可以立即获得宽和高，坏处是多了一次测量过程。
     * 至于为什么参数是LayoutParams.WRAP_CONTENT，那是因为我假设这个view的layout_width和layout_height为wrap_content,因为如果为一个确切的值，还有必要测量吗？
     */
    private void getWidthHeight1() {

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

//        textViewMain.measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewMain.measure(w,h);
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
     *
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

}

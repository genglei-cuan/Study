package cn.steve.pullscrollview.ctrip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 下拉放大
 *
 * Created by yantinggeng on 2015/12/4.
 */
public class PullToZoomScollView extends LinearLayout {

    public PullToZoomScollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToZoomScollView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}

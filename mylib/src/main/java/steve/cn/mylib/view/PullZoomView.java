package steve.cn.mylib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 做一个仿造携程下拉放大头部的view，向上滚动一起滚动的控件
 *
 * Created by Steve on 2016/6/5.
 */
public class PullZoomView extends LinearLayout {

    private View headView;
    private float headHeight;
    private float touchY;
    private float lastTouchY;
    private float maxZoomDelta;
    private boolean isMax = false;
    //最小滑动距离
    private int scaledTouchSlop;

    public PullZoomView(Context context) {
        super(context);
    }

    public PullZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        scaledTouchSlop = ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;
//    return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastTouchY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                float delta = y - lastTouchY;
                if (delta > 0) {
                    if (delta < maxZoomDelta) {
                        setHeadViewHeight(delta);
                    } else {
                        isMax = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isMax) {
                    setHeadViewHeight(-maxZoomDelta);
                } else {
                    float d = lastTouchY - y;
                    setHeadViewHeight(d);
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    private void setHeadViewHeight(float delta) {
        ViewGroup.LayoutParams layoutParams = headView.getLayoutParams();
        layoutParams.height += delta;
        headView.setLayoutParams(layoutParams);
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    public void setMaxZoomDelta(float maxZoomDelta) {
        this.maxZoomDelta = maxZoomDelta;
    }


}

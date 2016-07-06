package steve.cn.mylib.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 做一个仿造携程下拉放大头部的view，向上滚动一起滚动的控件
 *
 * Created by Steve on 2016/6/5.
 */
public class PullZoomView extends LinearLayout {

    private Point point = new Point();
    private ViewDragHelper mDragHelper;
    private View headView;
    private int headHeight;
    private int maxDelta;

    public PullZoomView(Context context) {
        super(context);
        init();
    }

    public PullZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
        headView = getChildAt(0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        point.x = headView.getLeft();
        point.y = headView.getTop();
        headHeight = headView.getHeight();
        maxDelta = headHeight / 2;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public int getViewVerticalDragRange(View child) {

            return super.getViewVerticalDragRange(child);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    }

}


package cn.steve.touchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class TouchLinearout extends LinearLayout {

    public TouchLinearout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TouchLinearout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLinearout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("TouchLinearout-->dispatchTouchEvent-->" + "down");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("TouchLinearout-->dispatchTouchEvent-->" + "up");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("TouchLinearout-->dispatchTouchEvent-->" + "move");
                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("TouchLinearout-->onInterceptTouchEvent-->" + "down");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("TouchLinearout-->onInterceptTouchEvent-->" + "up");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("TouchLinearout-->onInterceptTouchEvent-->" + "move");
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("TouchLinearout-->onTouchEvent-->" + "down");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("TouchLinearout-->onTouchEvent-->" + "up");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("TouchLinearout-->onTouchEvent-->" + "move");
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

}

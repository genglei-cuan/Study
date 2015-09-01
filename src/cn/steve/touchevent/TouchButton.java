
package cn.steve.touchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class TouchButton extends Button {

    public TouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchButton(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("Button-->dispatchTouchEvent-->" + "down");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Button-->dispatchTouchEvent-->" + "up");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Button-->dispatchTouchEvent-->" + "move");
                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("Button-->onTouchEvent-->" + "down");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Button-->onTouchEvent-->" + "up");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Button-->onTouchEvent-->" + "move");
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

}

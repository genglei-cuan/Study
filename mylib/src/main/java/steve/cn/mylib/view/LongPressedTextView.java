package steve.cn.mylib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 长按监听和抬起监听
 *
 * Created by yantinggeng on 2016/9/3.
 */
public class LongPressedTextView extends TextView {

    private GestureDetector gestureDetector;
    private GestureDetector.OnGestureListener gestureListener;
    private PressedChangedListener pressedChangedListener;

    public LongPressedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                if (pressedChangedListener != null) {
                    pressedChangedListener.onLongPress();
                }
            }
        };
        gestureDetector = new GestureDetector(getContext(), gestureListener);
    }


    public void setPressedChangedListener(PressedChangedListener pressedChangedListener) {
        this.pressedChangedListener = pressedChangedListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureListener != null) {
            gestureDetector.onTouchEvent(event);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (pressedChangedListener != null) {
                pressedChangedListener.onUp();
            }
        }
        return true;
    }

    public interface PressedChangedListener {

        void onLongPress();

        void onUp();
    }

}

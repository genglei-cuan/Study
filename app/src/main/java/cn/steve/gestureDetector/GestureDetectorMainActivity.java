package cn.steve.gestureDetector;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;

import butterknife.ButterKnife;
import cn.steve.study.R;

/**
 * 手势监听的demo，下拉显示状态栏
 * <p/>
 * Created by Steve on 2015/8/20.
 */
public class GestureDetectorMainActivity extends AppCompatActivity {

    private int SWIPE_MIN_DISTANCE = 200;
    private int SWIPE_MAX_OFF_PATH = 250;
    private int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        ButterKnife.bind(this);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        ViewConfiguration vc = ViewConfiguration.get(this.getApplicationContext());
        //最小的滑动距离
        SWIPE_MIN_DISTANCE = vc.getScaledPagingTouchSlop();
        //最小的滑动速度
        SWIPE_THRESHOLD_VELOCITY = vc.getScaledMinimumFlingVelocity();
        //最大的滑动距离，用来区分是不是滑动
        SWIPE_MAX_OFF_PATH = vc.getScaledTouchSlop();
    }

    public void showStatusBar() {
        try {
            Object sbservice = getSystemService("statusbar");
            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
            Method showsb;
            if (Build.VERSION.SDK_INT >= 17) {
                showsb = statusbarManager.getMethod("expandNotificationsPanel");
            } else {
                showsb = statusbarManager.getMethod("expand");
            }
            showsb.invoke(sbservice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            try {
                // 上拉
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    showStatusBar();
                    return true;
                }
                //下拉
                else if (Math.abs(e2.getY() - e1.getY()) > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    showStatusBar();
                    return true;
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
    }
}

package cn.steve.floatingview.simple;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.steve.study.R;
import steve.cn.mylib.commonutil.ScreenUtils;

/**
 * Created by yantinggeng on 2016/2/1.
 */
public class FloatingUtil {

    //定义浮动窗口布局
    private LinearLayout mFloatLayout;
    private WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    private WindowManager mWindowManager;
    private ImageButton mFloatView;
    private Activity context;

    public FloatingUtil(Activity context) {
        this.context = context;
    }

    public void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        //通过getApplication获取的是WindowManagerImpl.CompatModeWrapper
        mWindowManager = (WindowManager) context.getApplication().getSystemService(Context.WINDOW_SERVICE);
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.START | Gravity.TOP;
        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(context);
        //获取浮动窗口视图所在布局
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.alert_window_menu, null);
        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        //浮动窗口按钮
        mFloatView = (ImageButton) mFloatLayout.findViewById(R.id.alert_window_imagebtn);

        //添加mFloatLayout
        mWindowManager.addView(mFloatLayout, wmParams);

        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        int measuredWidth = mFloatLayout.getMeasuredWidth();
        int measuredHeight = mFloatLayout.getMeasuredHeight();

        System.out.println("measuredHeight:" + measuredHeight + "measuredWidth:" + measuredWidth);

        wmParams.x = ScreenUtils.getScreenWidth(context) - measuredWidth * 2;
        wmParams.y = ScreenUtils.getScreenHeight(context) - measuredHeight * 2;

        mWindowManager.updateViewLayout(mFloatLayout, wmParams);

        //设置监听浮动窗口的触摸移动
        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            boolean isClick;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFloatView.setBackgroundResource(R.drawable.share_icon_copy);
                        isClick = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isClick = true;
                        // getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
                        wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth() / 2;
                        // 减25为状态栏的高度
                        wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight() / 2 - 75;
                        // 刷新
                        mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                        return true;
                    case MotionEvent.ACTION_UP:
                        mFloatView.setBackgroundResource(R.drawable.share_icon_more);
                        return isClick;// 此处返回false则属于移动事件，返回true则释放事件，可以出发点击否。

                    default:
                        break;
                }
                return false;
            }
        });

        mFloatView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "一百块都不给我！", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onDestroy() {
        if (mFloatLayout != null) {
            //移除悬浮窗口
            mWindowManager.removeView(mFloatLayout);
        }
    }

}

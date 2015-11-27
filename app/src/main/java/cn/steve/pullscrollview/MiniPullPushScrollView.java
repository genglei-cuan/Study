package cn.steve.pullscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by yantinggeng on 2015/11/27.
 */
public class MiniPullPushScrollView extends ScrollView {


    // 默认阻尼系数
    public static final float DEFAULT_SCROLL_RATIO = 0.5f;

    //用于收缩和放大的head
    private View head = null;
    //内容view
    private View contentView = null;

    //head 的正常高度
    private int headHeight = 0;
    private int headWidth = 0;
    //head 设置的高度
    private float mCurrentHeadHeight = 0;

    //高度的扩大比例
    private float headHeightScale = 1.1f;

    //触摸的Y轴坐标
    private float downY = 0;
    //当前的状态
    private State mState;


    public MiniPullPushScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MiniPullPushScrollView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                if (headHeight == 0) {
                    headHeight = head.getMeasuredHeight();
                    headWidth = head.getMeasuredWidth();
                }
                return super.onTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                //误差操作规避
                float deltaY = Math.abs(ev.getY() - downY);
                if (deltaY < 10) {
                    break;
                }
                doMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                //假如是正在下拉，则恢复正常状态
                if (mState == State.DOWN) {
                    doBack();
                    mState = State.NORMAL;
                }
                if (mState == State.UP) {
                    doBack();
                    mState = State.NORMAL;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    //回滚到正常状态
    private void doBack() {
    }

    //处理滑动
    private void doMove(MotionEvent ev) {
        float deltaY = Math.abs(ev.getY() - downY);
        mCurrentHeadHeight = headHeight + deltaY * DEFAULT_SCROLL_RATIO;


    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    //定义控件所处的状态
    private enum State {
        //当前处于正常状态
        NORMAL,
        //下拉状态
        DOWN,
        //上拉状态
        UP,
        //处于顶端，已经收缩完成
        TOP
    }


}

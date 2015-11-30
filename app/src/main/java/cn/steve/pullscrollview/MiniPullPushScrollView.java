package cn.steve.pullscrollview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by yantinggeng on 2015/11/27.
 */
public class MiniPullPushScrollView extends ScrollView {

    // 默认阻尼系数
    public static final float DEFAULT_SCROLL_RATIO = 0.5f;
    //内容view
    private View contentView = null;

    /**
     * head部分在处于正在状态的情况下，在下拉的时候需要被拉伸
     */

    //用于收缩和放大的head，将内部的第一个子view作为head
    private View head = null;
    //head 的正常高度
    private int headHeight = 0;
    private int headWidth = 0;
    //head 设置的高度，通过设置高度，来实现下拉看似放大的效果
    private float mCurrentHeadHeight = 0;
    //高度的扩大比例，因为不能无限的放大head的高度，对外提供这个比例，实现head高度的最大值计算
    private float headHeightScale = 3f;
    private float maxHeadHeight = headHeight;

    //触摸的Y轴坐标
    private float downY = 0;

    //当前的状态
    private State mState;

    //正常状态下，头部已经上滑了半个大小（可以调用自动滑动到头部）
    private boolean isAuto2Top = false;


    public MiniPullPushScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    public MiniPullPushScrollView(Context context) {
        super(context);
//        init();
    }

    //初始化一些数据量
    private void init() {
        //第一个子view作为head
        head = ((ViewGroup) getChildAt(0)).getChildAt(0);
        //head的高度
        mCurrentHeadHeight = headHeight = head.getMeasuredHeight();
        headWidth = head.getMeasuredWidth();
        //最大高度
        maxHeadHeight = headHeight * headHeightScale;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                if (headHeight == 0) {
                    init();
//                    headHeight = head.getMeasuredHeight();
//                    headWidth = head.getMeasuredWidth();
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
                    rollUp2Normal();
                }
                if (mState == State.UP) {
                    //判断是否需要自动的滚回去
                    if (mCurrentHeadHeight > headHeight / 2) {
                        rollDown2Normal();
                    } else {
                        rollUp2Top();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    //上滑的时候，松手，下滑回滚到正常状态
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rollDown2Normal() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mCurrentHeadHeight, headHeight);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                head.getLayoutParams().height = (int) value;
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mState = State.NORMAL;
            }
        });
        valueAnimator.start();
    }

    //上滑的时候，松手，上滑到顶端，到达TOP状态
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rollUp2Top() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mCurrentHeadHeight, 0);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                head.getLayoutParams().height = (int) value;
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mState = State.TOP;
            }
        });
        valueAnimator.start();
    }


    //通过正常的滑动,完成上滑的逻辑
    private void doUp(MotionEvent ev) {
        mState = State.UP;
        //do nothing
        super.onTouchEvent(ev);
    }

    //通过正常的滑动，完成下滑的逻辑
    private void doDown(MotionEvent ev) {
        mState = State.DOWN;
        //do nothing
        super.onTouchEvent(ev);
    }

    //下滑，执行放大 //TODO 模糊
    private void doDownEnlarge(float deltaY) {
        if (mCurrentHeadHeight <= maxHeadHeight) {
            mState = State.EXPANDING;
            //改变头部的高度
            mCurrentHeadHeight = headHeight + deltaY * DEFAULT_SCROLL_RATIO;
            head.getLayoutParams().height = (int) mCurrentHeadHeight;
            requestLayout();
        } else {
            //切换状态，表示已经到达最底端
            mState = State.BOTTOM;
        }
    }

    //下拉的时候，松手回滚到正常状态
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rollUp2Normal() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mCurrentHeadHeight, headHeight);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                head.getLayoutParams().height = (int) value;
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mState = State.NORMAL;
            }
        });
        valueAnimator.start();
    }

    //处理滑动
    private void doMove(MotionEvent ev) {
        float deltaY = ev.getY() - downY;
        //下拉
        if (deltaY > 0) {
            //下拉放大
            if (mCurrentHeadHeight >= headHeight) {
                doDownEnlarge(deltaY);
            } else {
                //正常的下拉
                doDown(ev);
            }
        }
        //上拉
        if (deltaY < 0) {
            if (mCurrentHeadHeight >= headHeight) {
                doDownEnlarge(deltaY);
            } else {
                doUp(ev);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    //定义控件所处的状态
    private enum State {
        //当前处于正常状态
        NORMAL,
        //处于顶端，已经收缩完成
        TOP,
        //处于底部，已经达到最底端
        BOTTOM,
        //正在处于扩展的状态
        EXPANDING,
        //下拉状态
        DOWN,
        //上拉状态
        UP,
    }


}

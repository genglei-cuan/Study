package cn.steve.pullscrollview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
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
    private int mCurrentHeadHeight = 0;
    //高度的扩大比例，因为不能无限的放大head的高度，对外提供这个比例，实现head高度的最大值计算
    private float headHeightScale = 1.2f;
    private int maxHeadHeight = headHeight;

    //触摸的Y轴坐标
    private int downY = 0;
    private PointF mStartPoint = new PointF();

    //当前的状态
    private State mState;
    //当前的方向
    private DIRECTION mDirection;

    //正常状态下，头部已经上滑了半个大小（可以调用自动滑动到头部）
    private boolean isAuto2Top = false;


    public MiniPullPushScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MiniPullPushScrollView(Context context) {
        super(context);
    }

    //初始化一些数据量
    private void init() {
        //第一个子view作为head
        head = ((ViewGroup) getChildAt(0)).getChildAt(0);
        //head的高度
        mCurrentHeadHeight = headHeight = head.getMeasuredHeight();
        headWidth = head.getMeasuredWidth();
        //最大高度
        maxHeadHeight = (int) (headHeight * headHeightScale);
        mState = State.NORMAL;
        mDirection = DIRECTION.STABLE;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartPoint.set(ev.getX(), ev.getY());
                downY = (int) ev.getY();
                if (headHeight == 0) {
                    init();
//                    headHeight = head.getMeasuredHeight();
//                    headWidth = head.getMeasuredWidth();
                }
                break;
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
                if (mState == State.EXPANDING || mState == State.BOTTOM) {
                    rollUp2Normal();
                }
                //TODO  进行自动回弹的判断
                if (mState != State.TOP) {
                    if (isAuto2Top) {
                        rollUp2Top();
                        mState = State.TOP;
                    } else {
                        rollDown2Normal();
                        mState = State.NORMAL;
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    //上滑的时候，松手，下滑回滚到正常状态
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rollDown2Normal() {
        scrollTo(0, 0);
    }

    //上滑的时候，松手，上滑到顶端，到达TOP状态
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rollUp2Top() {
        scrollTo(0, headHeight);
    }


    //通过正常的滑动,完成上滑的逻辑
    private void doUp(MotionEvent ev) {
        int delta = (int) Math.abs(ev.getY() - downY);
        //到达了top状态之后，之后的上滑直接滑动即可
        if (mState != State.BOTTOM) {
            return;
        } else {
            //TODO 假如是从底部滑上去的，需要先恢复原状的大小
            doSmall2Normal(delta);
        }
        //do nothing
    }

    //通过正常的滑动，完成下滑的逻辑
    private void doDown(MotionEvent ev) {
        //do nothing
    }

    //下滑，执行放大 //TODO 模糊
    private void doDownEnlarge(float deltaY) {
        if (mCurrentHeadHeight <= maxHeadHeight) {
            mState = State.EXPANDING;
            //改变头部的高度
            mCurrentHeadHeight = (int) (headHeight + deltaY * DEFAULT_SCROLL_RATIO);
            ViewGroup.LayoutParams layoutParams = head.getLayoutParams();
            layoutParams.height = mCurrentHeadHeight;
            head.setLayoutParams(layoutParams);
            requestLayout();
        } else {
            //切换状态，表示已经到达最底端
            mState = State.BOTTOM;
        }
    }

    //上滑，缩小head到正常大小
    private void doSmall2Normal(float deltaY) {
        if (mCurrentHeadHeight > headHeight) {
            mState = State.EXPANDING;
            //改变头部的高度
            mCurrentHeadHeight += (int) (deltaY * DEFAULT_SCROLL_RATIO);
            ViewGroup.LayoutParams layoutParams = head.getLayoutParams();
            layoutParams.height = mCurrentHeadHeight;
            head.setLayoutParams(layoutParams);
            requestLayout();
        } else {
            //从拉伸状态恢复到正常状态，注意此时onScroll里也会进行相应的设置
            mState = State.NORMAL;
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
                mCurrentHeadHeight = (int) value;
                ViewGroup.LayoutParams layoutParams = head.getLayoutParams();
                layoutParams.height = mCurrentHeadHeight;
                head.setLayoutParams(layoutParams);
                requestLayout();
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
            mDirection = DIRECTION.DOWN;
            //下拉放大
            if (mState == State.NORMAL || mState == State.EXPANDING) {
                doDownEnlarge(deltaY);
            } else {
                //正常的下拉
                doDown(ev);
            }
        }
        //上拉
        if (deltaY < 0) {
            mDirection = DIRECTION.UP;
            doUp(ev);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //滑动到head全部露出的时候，状态切换
        if (getScrollY() == 0) {
            mState = State.NORMAL;
        }
        if (getScrollY() > 0 && getScrollY() < headHeight / 2) {
            isAuto2Top = false;
            mState = State.SHRINKING;
        }
        if (getScrollY() >= headHeight / 2 && getScrollY() < headHeight) {
            isAuto2Top = true;
            mState = State.SHRINKING;
        }
        if (getScrollY() >= headHeight) {
            isAuto2Top = false;
            mState = State.TOP;
        }
    }

    //定义head所处的状态
    private enum State {
        //当前处于正常状态
        NORMAL,
        //处于顶端，已经收缩完成
        TOP,
        //处于底部，已经达到最底端
        BOTTOM,
        //正在处于扩展的状态
        EXPANDING,
        //露出部分,在收缩
        SHRINKING

    }

    /**
     * 定义手势的
     */
    private enum DIRECTION {
        //进行上拉
        UP,
        //下拉状态
        DOWN,
        //静止
        STABLE
    }


}

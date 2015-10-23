package cn.steve.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import cn.steve.study.R;


/**
 * 测试属性动画的demo
 */
public class AnimatorActivity extends Activity {

  private View mContentView;
  private View mLoadingView;
  private int mShortAnimationDuration;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animator);
    mContentView = findViewById(R.id.content);
    mLoadingView = findViewById(R.id.hidetent);

    // 初始化隐藏这个View.
    // mContentView.setVisibility(View.GONE);

    // 获取并缓存系统默认的“短”时长
    mShortAnimationDuration = 100;

    crossfade();
    // together2();
    mContentView.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        float y1 = 0;
        float y2 = 0;
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            y1 = event.getY();
            break;

          case MotionEvent.ACTION_UP:
            y2 = event.getY();
            if (y2 - y1 > 10) {
              mLoadingView.setVisibility(View.VISIBLE);
            }
          default:
            break;
        }
        return false;
      }
    });

  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
  private void crossfade() {
    // 设置内容View为0%的不透明度，但是状态为“可见”，
    // 因此在动画过程中是一直可见的（但是为全透明）。
    mContentView.setAlpha(0f);
    mContentView.setVisibility(View.VISIBLE);
    // 开始动画内容View到100%的不透明度，然后清除所有设置在View上的动画监听器。
    mContentView.animate().alpha(1f).setDuration(mShortAnimationDuration)
        .setListener(null);
    // 加载View开始动画逐渐变为0%的不透明度，
    // 动画结束后，设置可见性为GONE（消失）作为一个优化步骤
    // （它将不再参与布局的传递等过程）
    mLoadingView.animate().alpha(0f).setDuration(mShortAnimationDuration)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            mLoadingView.setVisibility(View.GONE);
          }
        });
  }

  @SuppressLint("NewApi")
  private void together() {

    ObjectAnimator contentAnimator = ObjectAnimator.ofFloat(mContentView,
                                                            "translationY", 200, 0);
    contentAnimator.setDuration(5000);
    contentAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

    AnimatorSet animatorSet = new AnimatorSet();

    animatorSet.setDuration(5000);
    animatorSet.playTogether(contentAnimator);
    animatorSet.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
      }

      @Override
      public void onAnimationEnd(Animator animation) {
      }

      @Override
      public void onAnimationCancel(Animator animation) {
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
      }
    });

    animatorSet.start();
  }

  public void together2() {
    ObjectAnimator contentAnimator = ObjectAnimator.ofFloat(mContentView,
                                                            "translationY", mContentView.getY(),
                                                            0);
    contentAnimator.setDuration(5000);
    contentAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

    ObjectAnimator translationUp = ObjectAnimator.ofFloat(mLoadingView,
                                                          "translationY", 0,
                                                          -mLoadingView.getY());
    translationUp.setInterpolator(new DecelerateInterpolator());
    translationUp.setDuration(5000);
    translationUp.start();
    AnimatorSet animatorSet = new AnimatorSet();

    animatorSet.setDuration(5000);
    animatorSet.playTogether(contentAnimator, translationUp);
    animatorSet.start();
  }

}

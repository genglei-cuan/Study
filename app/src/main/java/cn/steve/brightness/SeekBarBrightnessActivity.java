package cn.steve.brightness;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import cn.steve.study.R;


public class SeekBarBrightnessActivity extends Activity {

  int[] location = new int[2];
  private Button mButton;
  private SeekBar mSeekBar;
  private ScreenBrightnessTool mScreenBrightnessTool;
  private ViewStub mViewStub;

  public static int dip2px(Context context, float dipValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seekbarbrightness);
    mScreenBrightnessTool = ScreenBrightnessTool.Builder(this);
    mButton = (Button) findViewById(R.id.button_seekbar);
    mViewStub = (ViewStub) findViewById(R.id.viewStub1);
    mButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        mButton.setEnabled(false);
        mSeekBar = (SeekBar) mViewStub.inflate();
        mButton.getLocationOnScreen(location);
        int height = SeekBarBrightnessActivity.dip2px(SeekBarBrightnessActivity.this, 200);
        Rect anchorRect = new Rect(location[0], location[1],
                                   location[0] + mButton.getWidth(),
                                   location[1] + mButton.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mSeekBar, "Y",
                                                             anchorRect.centerY(),
                                                             anchorRect.top);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(translationY);// 两个动画同时开始
        // animatorSet.start();
        mSeekBar.setMax(100);
        mSeekBar.setProgress(0);
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {
          }

          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mScreenBrightnessTool.setBrightness(progress);
          }
        });
      }
    });
  }
}

package cn.steve.touchevent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import cn.steve.study.R;

public class TouchMainActivity extends Activity {

  private TouchButton mButton;

  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    super.setContentView(R.layout.acitivity_touch);
    mButton = (TouchButton) findViewById(R.id.button_touch);

    mButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    mButton.setOnTouchListener(new OnTouchListener() {
      @SuppressLint("ClickableViewAccessibility")
      @Override
      public boolean onTouch(View v, MotionEvent event) {

        return false;

      }
    });
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        System.out.println("activity-->dispatchTouchEvent-->" + "down");
        break;
      case MotionEvent.ACTION_UP:
        System.out.println("activity-->dispatchTouchEvent-->" + "up");
        break;
      case MotionEvent.ACTION_MOVE:
        System.out.println("activity-->dispatchTouchEvent-->" + "move");
        break;

      default:
        break;
    }

    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {

    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        System.out.println("activity-->onTouchEvent-->" + "down");
        break;
      case MotionEvent.ACTION_UP:
        System.out.println("activity-->onTouchEvent-->" + "up");
        break;
      case MotionEvent.ACTION_MOVE:
        System.out.println("activity-->onTouchEvent-->" + "move");
        break;

      default:
        break;
    }
    return super.onTouchEvent(ev);
  }

}

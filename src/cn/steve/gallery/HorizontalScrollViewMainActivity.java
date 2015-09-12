package cn.steve.gallery;

import cn.steve.study.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HorizontalScrollViewMainActivity extends Activity {

  //水平滚动的控件
  private HorizontalScrollView horizontalScrollView;
  private LinearLayout linearLayout;
  // 滚动条的宽度
  private int hsv_width;
  // 总共有多少个view
  private int child_count;
  // 每一个view的宽度
  private int child_width;
  // 预计显示在屏幕上的view的个数
  private int child_show_count;
  // 一开始居中选中的view
  private int child_start;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_horizontalscrollview);
    init();
  }

  /**
   * 初始化控件及变量
   */
  private void init() {
    horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
    linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    child_count = 31;
    child_show_count = 7;
    child_start = 4;
  }

  /**
   * 给滚动控件添加view，只有重复两个列表才能实现循环滚动
   */
  private void initData() {
    for (int i = 0; i < child_count; i++) {
      TextView textView = new TextView(this);
      textView.setLayoutParams(new ViewGroup.LayoutParams(child_width,
          ViewGroup.LayoutParams.MATCH_PARENT));
      textView.setText("" + (i + 1));
      textView.setGravity(Gravity.CENTER);
      linearLayout.addView(textView);
    }
    for (int i = 0; i < child_count; i++) {
      TextView textView = new TextView(this);
      textView.setLayoutParams(new ViewGroup.LayoutParams(child_width,
          ViewGroup.LayoutParams.MATCH_PARENT));
      textView.setText("" + (i + 1));
      textView.setGravity(Gravity.CENTER);
      linearLayout.addView(textView);
    }
  }

  /**
   * 实现滚动的循环处理，及停止触摸时的处理
   */
  private void initHsvTouch() {
    horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {

      private int pre_item;

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        boolean flag = false;
        int x = horizontalScrollView.getScrollX();
        int current_item = (x + hsv_width / 2) / child_width + 1;
        switch (event.getAction()) {
          case MotionEvent.ACTION_MOVE:
            flag = false;
            if (x <= child_width) {
              horizontalScrollView.scrollBy(child_width * child_count, 0);
              current_item += child_count;
              
              
            } else if (x >= (child_width * child_count * 2 - hsv_width - child_width)) {
              horizontalScrollView.scrollBy(-child_width * child_count, 0);
              current_item -= child_count;
              
              
              
            }
            break;
          case MotionEvent.ACTION_UP:
            flag = true;
            horizontalScrollView.smoothScrollTo(child_width * current_item - child_width / 2
                - hsv_width / 2, horizontalScrollView.getScrollY());
            
            //确定已选中
            
            break;
        }
        if (pre_item == 0) {
          isChecked(current_item, true);
        } else if (pre_item != current_item) {
          isChecked(pre_item, false);
          isChecked(current_item, true);
        }
        pre_item = current_item;
        return flag;
      }
    });
  }

  /**
   * 设置指定位置的状态
   * 
   * @param item
   * @param isChecked
   */
  private void isChecked(int item, boolean isChecked) {
    TextView textView = (TextView) linearLayout.getChildAt(item - 1);
    if (isChecked) {
      textView.setTextColor(Color.RED);
    } else {
      textView.setTextColor(Color.BLACK);
    }
  }

  /**
   * 刚开始进入界面时的初始选中项的处理
   */
  private void initStart() {
    final ViewTreeObserver observer = horizontalScrollView.getViewTreeObserver();
    observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

      @Override
      public boolean onPreDraw() {
        observer.removeOnPreDrawListener(this);
        int child_start_item = child_start;
        if ((child_start * child_width - child_width / 2 - hsv_width / 2) <= child_width) {
          child_start_item += child_count;
        }
        horizontalScrollView.scrollTo(child_width * child_start_item - child_width / 2 - hsv_width
            / 2, horizontalScrollView.getScrollY());
        isChecked(child_start_item, true);
        return false;
      }
    });
  }

  /**
   * 只有到了这个方法才能获取控件的尺寸
   */
  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    // TODO Auto-generated method stub
    super.onWindowFocusChanged(hasFocus);
    hsv_width = horizontalScrollView.getWidth();
    int child_width_temp = hsv_width / child_show_count;
    if (child_width_temp % 2 != 0) {
      child_width_temp++;
    }
    child_width = child_width_temp;
    initData();
    initHsvTouch();
    initStart();
  }

}

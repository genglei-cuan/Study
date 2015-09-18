
package cn.steve.gallery;

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
import cn.steve.Utils.CalendarUntil;
import cn.steve.study.R;

/**
 * 后往前是从第六个开始跳转的
 * 
 * @author Steve
 */
public class HorizontalScrollViewMainActivity extends Activity {

    // 水平滚动的控件
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

    private int currentYear = 2015;
    private int currenMonth = 1;
    private int currenDate = 1;
    private int currenDaysOfMonth = 30;
    // 用来记录接下来跳转到的日期相关信息
    private int desYear = 2016;
    private int desMonth = 2;
    private int desDate = 1;
    private int desDaysOfMonth = 30;
    // 标识需要切换当前月份，年，日期的位置
    private int desIndexToChangeDateYearMonth = 100;
    private int desIndexToChangeIndexPreNext = 100;
    // 记录变化前后的子孩子数目的差值
    private int childCountDelta = 0;
    private boolean isDeleteRight = false;

    private int desIndexToChangeAdd = 100;
    private boolean isDeleteLeft = false;

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
        child_count = CalendarUntil.getCurrenMonthDays();
        child_show_count = 7;
        currentYear = CalendarUntil.getCurrentYear();
        currenMonth = CalendarUntil.getCurrentMonth();
        currenDate = CalendarUntil.getCurrentDate();
        currenDaysOfMonth = CalendarUntil.getCurrenMonthDays();

        child_start = currenDate;

    }

    /**
     * 给滚动控件添加view，只有重复两个列表才能实现循环滚动
     */
    private void initData() {
        for (int i = 0; i < child_count; i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(child_width,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            // TODO 日期的逻辑
            textView.setText(currenMonth + "月" + (i + 1));
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }

        for (int i = 0; i < child_count; i++) {

            TextView textView = new TextView(this);

            textView.setLayoutParams(new ViewGroup.LayoutParams(child_width,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText(currenMonth + "月" + (i + 1));
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

                        // 切换当前的年月日
                        if (current_item == desIndexToChangeDateYearMonth) {
                            currenDate = desDate;
                            currenMonth = desMonth;
                            currentYear = desYear;
                            currenDaysOfMonth = desDaysOfMonth;
                        }

                        // 过了用户可以返回的盲区
                        if (current_item == desIndexToChangeIndexPreNext) {
                            if (isDeleteRight) {
                                toPreMonthDeleRight();
                            } else {
                                toPreMonthAddRight();
                            }
                            // 刷一遍后面的内容
                            TextView textView;
                            for (int i = 0; i < child_count; i++) {
                                textView = (TextView) linearLayout.getChildAt(child_count + i);
                                textView.setText(desMonth + "月" + (i + 1));
                            }
                        }

                        if (current_item == desIndexToChangeAdd) {
                            if (isDeleteLeft) {
                                toNextMonthDeleteLeft();
                            } else {
                                toNextMonthAddLeft();
                            }
                            // 刷一遍前面的内容
                            TextView textView;
                            for (int i = 0; i < child_count; i++) {
                                textView = (TextView) linearLayout.getChildAt(i);
                                textView.setText(desMonth + "月" + (i + 1));
                            }
                        }

                        flag = false;

                        // 从这里进入上个月，向左滑动到达中间位置的时候，跳转到循环页面的相应位置 5-》A5
                        if (x <= child_width) {
                            // 进入上个月
                            int preMonthDays = CalendarUntil.getMonthDaysByOffset(-1);
                            if (preMonthDays < currenDaysOfMonth) {
                                // 在后面删掉两倍的d个textview
                                int d = currenDaysOfMonth - preMonthDays;
                                int smallIndex, bigIndex;

                                // 这里只能删除前面的， 不能删除后面，未来可能回头。
                                for (int i = 0; i < d; i++) {
                                    smallIndex = child_count - 1 - i;
                                    System.out.println("SteveDateSmallIndex:" + smallIndex);
                                    linearLayout.removeViewAt(smallIndex);
                                }

                                childCountDelta = -d;
                                child_count += childCountDelta;
                                isDeleteRight = true;
                            }

                            if (preMonthDays > currenDaysOfMonth) {
                                int d = preMonthDays - currenDaysOfMonth;
                                // 为上个月补足日期
                                for (int i = 0; i < d; i++) {
                                    TextView textView = new TextView(
                                            HorizontalScrollViewMainActivity.this);
                                    // TODO 添加设置文本的逻辑
                                    textView.setText("H");
                                    textView.setLayoutParams(new ViewGroup.LayoutParams(
                                            child_width, ViewGroup.LayoutParams.MATCH_PARENT));
                                    textView.setGravity(Gravity.CENTER);
                                    linearLayout.addView(textView, child_count + i);
                                }

                                childCountDelta = d;
                                child_count += childCountDelta;
                                isDeleteRight = false;

                            }

                            // 滑动到下个循环的部分
                            horizontalScrollView.scrollBy(child_width * child_count, 0);
                            current_item += child_count;

                            // TODO 从当前位置往前推算上个这个位置-3,同时要注意-3位置的后三个
                            int delta = current_item % child_count - child_start;
                            // 往前再走三个的位置就完全进入了上个月
                            desIndexToChangeDateYearMonth = current_item - 3;
                            // TODO
                            // 当前位置的前三个和后面全部的内容都需要修改的下标位置,这里给的是5是为了保证一定到达了可能跳转的位置
                            desIndexToChangeIndexPreNext = child_count - 5;
                            desDate = CalendarUntil.getLastDayOfMonth();
                            desMonth = CalendarUntil.getCurrentMonth();
                            desYear = CalendarUntil.getCurrentYear();
                            desDaysOfMonth = preMonthDays;
                            modifyLeftData(current_item);
                        }

                        // 从这里进入下个月，向右滑动的时候到达循环边的中间的时候，跳转到前面的相应位置 A27->27
                        else if (x >= (child_width * child_count * 2 - hsv_width - child_width)) {
                            // 即将进入下个月
                            int nextMonthDays = CalendarUntil.getMonthDaysByOffset(1);
                            if (nextMonthDays < currenDaysOfMonth) {
                                int d = currenDaysOfMonth - nextMonthDays;
                                // 在后面d个textview
                                for (int i = 0; i < d; i++) {
                                    linearLayout.removeViewAt(2 * child_count - 2 - i);
                                }
                                childCountDelta = -d;
                                child_count += childCountDelta;
                                isDeleteLeft = true;
                            }

                            if (nextMonthDays > currenDaysOfMonth) {
                                int d = nextMonthDays - currenDaysOfMonth;
                                for (int i = 0; i < d; i++) {
                                    TextView textView = new TextView(
                                            HorizontalScrollViewMainActivity.this);
                                    textView.setText("Hello");
                                    textView.setLayoutParams(new ViewGroup.LayoutParams(
                                            child_width, ViewGroup.LayoutParams.MATCH_PARENT));
                                    textView.setGravity(Gravity.CENTER);
                                    linearLayout.addView(textView, 2 * child_count + i);
                                }
                                childCountDelta=d;
                                child_count += childCountDelta;
                                isDeleteLeft = false;
                            }

                            horizontalScrollView.scrollBy(-child_width * child_count, 0);
                            current_item -= child_count;
                            // TODO 当前位置到下次这个位置+3，同时注意他的前三个
                            int delta = current_item % child_count - child_start;
                            desIndexToChangeDateYearMonth = current_item % child_count + 3;
                            desIndexToChangeAdd = child_count +5;
                            desDate = 1;
                            desMonth = CalendarUntil.getCurrentMonth();
                            desYear = CalendarUntil.getCurrentYear();
                            desDaysOfMonth = nextMonthDays;
                            modifyRightData(current_item);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        flag = true;
                        horizontalScrollView.smoothScrollTo(child_width * current_item
                                - child_width / 2
                                - hsv_width / 2, horizontalScrollView.getScrollY());
                        // 确定已选中

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

            // 进入上个月而在末尾添加一些上次未添加的内容，为的也是防止用户再回头
            private void toPreMonthAddRight() {
                int index = 0;
                for (int i = 0; i < Math.abs(childCountDelta); i++) {
                    TextView textView2 = new TextView(HorizontalScrollViewMainActivity.this);
                    textView2.setText("H");
                    textView2.setLayoutParams(new ViewGroup.LayoutParams(child_width,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    textView2.setGravity(Gravity.CENTER);

                    index = child_count + (child_count - childCountDelta) + i;

                    linearLayout.addView(textView2, index);
                }
            }

            // 进入上个月之后进行的删除上次漏掉没删除的后面部分，之前没删除是因为防止用户再回头
            private void toPreMonthDeleRight() {
                int bigIndex;
                for (int i = 0; i < Math.abs(childCountDelta); i++) {
                    bigIndex = child_count + (child_count - childCountDelta) - i - 1;
                    System.out.println("SteveDateBigIndex:" + bigIndex);
                    linearLayout.removeViewAt(bigIndex);
                }
            }

            // 进入下个月而进行的删除操作，删除上个月残留的日期个数
            private void toNextMonthDeleteLeft() {
                int smallIndex;
                for (int i = 0; i < Math.abs(childCountDelta); i++) {
                    smallIndex = child_count - childCountDelta + i - 1;
                    linearLayout.removeViewAt(smallIndex);
                }
            }

            // 进入下个也而仅需的添加操作
            private void toNextMonthAddLeft() {
                int smallIndex;
                for (int i = 0; i < Math.abs(childCountDelta); i++) {
                    smallIndex = child_count - childCountDelta + i - 1;
                    TextView textView2 = new TextView(HorizontalScrollViewMainActivity.this);
                    textView2.setText("H");
                    textView2.setLayoutParams(new ViewGroup.LayoutParams(child_width,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    textView2.setGravity(Gravity.CENTER);
                    linearLayout.addView(textView2, smallIndex);
                }
            }

        });
    }

    // 修改左边的日期内容
    private void modifyLeftData(int currentIndex) {
        System.out.println("currentIndex:" + currentIndex);
        System.out.println("currenMonth:" + currenMonth);
        TextView textView;

        // 预备跳转到上个月,应有位置被加了整个count，从当前位置的前三个往前至前排对应当前位置处设置为上个月的内容
        for (int i = currentIndex - 5, j = 0; i > currentIndex % child_count; i--, j++) {
            textView = (TextView) linearLayout.getChildAt(i - 1);
            textView.setText(desMonth + "月" + (desDaysOfMonth - j));
        }
        // TODO 当前位置的后面部分也要修改

        // 同时还有本来到达位置的前三个，不然会出现错误数据(TODO 描述清楚)
        int correntIndex = currentIndex % child_count;
        for (int i = correntIndex; i > 0; i--) {
            textView = (TextView) linearLayout.getChildAt(i - 1);
            textView.setText(desMonth + "月" + (i));
        }

    }

    // 修改右边的日期内容
    private void modifyRightData(int currentIndex) {
        TextView textView;
        // 准备下个月的数据
        for (int i = currentIndex + 5, j = 0; i < currentIndex + child_count; i++, j++) {
            textView = (TextView) linearLayout.getChildAt(i - 1);
            textView.setText(desMonth + "月" + (desDaysOfMonth - j));
        }
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
                horizontalScrollView.scrollTo(child_width * child_start_item - child_width / 2
                        - hsv_width
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

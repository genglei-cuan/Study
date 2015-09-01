
package cn.steve.bottomtext;

import java.util.List;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("ViewConstructor")
public class BottomPopupMenu extends PopupWindow {
    public final int TITLE_ID = 123456;

    private TitleChangeListener listner;

    /**
     * 菜单栏的整体布局LinearLayout
     */
    private LinearLayout linearLayout;

    /**
     * 菜单栏分类标题布局GridView
     */
    private GridView gv_title;

    private ScrollView scrollview;

    /**
     * 菜单栏功能图标与名称GridView
     */
    private TextView gv_body;
    /**
     * 菜单栏分类标题GridView的适配
     */
    private BottomTitleAdapter titleAdapter;

    private Context context;

    /**
     * 当前选中的分类标题
     */
    private int currentIndex = 0;

    /**
     * 上一次选中的分类标题 用于选择分类标题时的左右移动动画，判断应该怎样移动
     */
    private int preIndex = 0;

    /**
     * 标题与功能布局中间的分界线 RelativeLayout + TextView
     */
    private RelativeLayout divisionLayout;

    /**
     * 屏幕宽度
     */
    private int screenWidth = 0;

    @SuppressWarnings("deprecation")
    public BottomPopupMenu(Context context, List<String> titles) {
        super(context);
        this.context = context;

        /**
         * 菜单栏的整体布局LinearLayout初始化
         */
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        // linearLayout.setLayoutParams(new
        // LayoutParams(LayoutParams.MATCH_PARENT, 200));
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        /**
         * 获取屏幕宽度
         */
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();

        /**
         * 分界线布局初始化
         */
        divisionLayout = new RelativeLayout(context);
        divisionLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
        divisionLayout.setBackgroundColor(Color.YELLOW);

        /**
         * 标题布局初始化
         */
        gv_title = new GridView(context);

        /**
         * 用于重新初始化adapter
         */
        final List<String> l = titles;
        final Context c = context;

        titleAdapter = new BottomTitleAdapter(titles, context, 0);

        /**
         * 设置被选中后，背景颜色不再是系统原有的黄色，改为TRANSPARENT
         */
        gv_title.setSelector(new ColorDrawable(Color.WHITE));
        gv_title.setAdapter(titleAdapter);

        /**
         * 设置GridView列数
         */
        gv_title.setNumColumns(titleAdapter.getCount());

        gv_title.setBackgroundColor(Color.WHITE);

        /**
         * 选择分类标题时的响应事件 实现滑动切换菜单
         */
        gv_title.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * 重新初始化adapter，为了改变标题选择颜色
                 */
                titleAdapter = new BottomTitleAdapter(l, c, position);

                preIndex = currentIndex;
                currentIndex = position;

                gv_title.setAdapter(titleAdapter);

                /**
                 * 分界线布局中的textView跟随选中标题移动位置的，设置为动画效果
                 */
                divisionTran(position);

                /**
                 * 用于功能图标GridView动画效果 TranslateAnimation方法中的参数设置暂时不太明确
                 * 似乎，参数都是相对于控件自身的位置 第一个参数是开始位置，第二个是结束位置 有时间会弄清楚
                 */
                Animation translateBody;
                if (preIndex < currentIndex) {

                    translateBody = new TranslateAnimation(screenWidth, 0, 0, 0);
                    translateBody.setDuration(500);
                    gv_body.startAnimation(translateBody);

                } else if (preIndex > currentIndex) {

                    translateBody = new TranslateAnimation(-screenWidth, 0, 0, 0);
                    translateBody.setDuration(500);
                    gv_body.startAnimation(translateBody);

                }
                gv_body.setText(listner.getContent(currentIndex));
            }

        });

        scrollview = new ScrollView(context);
        scrollview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        // scrollview.setVerticalScrollBarEnabled(true);
        // scrollview.setFillViewport(true);

        gv_body = new TextView(context);
        gv_body.setBackgroundColor(Color.WHITE);
        gv_body.setTextSize(20);
        // gv_body.setPadding(0, 10, 0, 10);

        gv_body.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        /**
         * 点击内容时的响应事件
         */
        gv_body.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(c, gv_body.getText(), Toast.LENGTH_SHORT).show();

            }
        });

        /**
         * 初始化textView位置
         */
        divisionTran(0);

        /**
         * 把三个子布局加入到整体布局中去
         */
        linearLayout.addView(gv_title);
        linearLayout.addView(divisionLayout);
        scrollview.addView(gv_body);
        linearLayout.addView(scrollview);

        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(200);
        this.setContentView(linearLayout);

        /**
         * 以下代码是为了解决，菜单栏出现后，不能响应再次按menu按键使菜单栏消失的问题
         * 在这个网址找到的答案http://blog.csdn.net/admin_/article/details/7278402 可以自己去看
         */

        this.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_MENU) && (BottomPopupMenu.this.isShowing())) {
                    BottomPopupMenu.this.dismiss();
                    titleAdapter = new BottomTitleAdapter(l, c, 0);
                    gv_title.setAdapter(titleAdapter);
                    return true;
                }
                return false;
            }
        });

    }

    /**
     * 分界线布局中的textView跟随选中标题移动位置的，设置为动画效果
     */
    public void divisionTran(int position) {

        /**
         * 先移除了RelativeLayout中原有的textView
         */
        divisionLayout.removeAllViews();

        /**
         * 重新设置textView布局属性 动态改变控件位置 第一步
         */
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(screenWidth / 7, LayoutParams.MATCH_PARENT);

        /**
         * 设置动画效果
         */
        Animation translateTextView;
        translateTextView =
                new TranslateAnimation((preIndex - currentIndex) * screenWidth / 3, 0, 0, 0);

        /**
         * 根据选中的标题确定布局 动态改变控件位置 第二步
         */
        if (position == 0) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        } else if (position == 1) {
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        } else {
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        }

        /**
         * 动态改变控件位置 第三步
         */
        TextView line = new TextView(context);
        line.setBackgroundColor(Color.WHITE);
        divisionLayout.addView(line, lp);

        /**
         * 设置动画执行时间
         */
        translateTextView.setDuration(200);

        /**
         * 启动动画
         */
        line.startAnimation(translateTextView);
    }

    // /设置默认的操作
    public void setDefaultContent() {

        gv_body.setText(listner.getContent(0));

    }

    // define a interface for the main thread to refresh the UI
    interface TitleChangeListener {
        public String getContent(int i);
    }

    public void setListner(TitleChangeListener listner) {
        this.listner = listner;
    }

}

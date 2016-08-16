package steve.cn.mylib.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import steve.cn.mylib.R;


/**
 * 对TitleBar的封装，使用的时候，直接添加到布局的顶层，当做一个普通的view即可
 *
 * Created by yantinggeng on 2016/8/12.
 */
public class LvmmToolBarView extends FrameLayout {

    private Activity context;
    private ViewGroup contentView = null, customTitleView = null;
    private Toolbar toolbar;
    private TextView titleTextView, toolBarRight;
    private Menu menu;

    public LvmmToolBarView(Context context) {
        this(context, null);
    }

    public LvmmToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = (Activity) context;
        initViews(context);
        initDefalut();
        initMenu();
        this.addView(contentView);
    }

    //初始化控件
    private void initViews(Context context) {
        contentView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this, false);
        toolbar = (Toolbar) contentView.findViewById(R.id.toolBar);
        titleTextView = (TextView) contentView.findViewById(R.id.toolBarTitle);
        toolBarRight = (TextView) contentView.findViewById(R.id.toolBarRight);
        customTitleView = (ViewGroup) contentView.findViewById(R.id.customTitleView);
    }

    //定义默认值
    private void initDefalut() {
        //默认是返回键
        toolbar.setNavigationIcon(R.drawable.v7_fanhui);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });
    }

    //初始化右边菜单
    private void initMenu() {
        //toolbar.inflateMenu(R.menu.toolbar_right);
        //menu = toolbar.getMenu();
    }

    /**
     * 设置自定义的Title部分
     *
     * @param titleView 替换的view
     */
    public void setCustomTitleView(View titleView) {
        this.titleTextView.setVisibility(GONE);
        customTitleView.addView(titleView);
    }

    /**
     * 设置右边的图标
     *
     * @param icon 图标id
     */
    public void setToolBarRightIcon(@DrawableRes int icon) {
        toolBarRight.setVisibility(VISIBLE);
        toolBarRight.setBackgroundResource(icon);
    }

    /**
     * 设置标题的颜色
     *
     * @param color 颜色资源文件
     */
    public void setBackgroundColor(@ColorRes int color) {
        contentView.setBackgroundColor(getResources().getColor(color));
    }


    /**
     * 设置居中的标题
     *
     * @param title 标题内容
     */
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    /**
     * 设置右边的文本
     *
     * @param text 文本内容
     */
    public void setToolBarRightTitle(String text) {
        toolBarRight.setVisibility(VISIBLE);
        toolBarRight.setText(text);
    }

    /**
     * 设置右边的文本的点击事件
     *
     * @param listener 点击监听器
     */
    public void setToolBarRightOnClickListener(OnClickListener listener) {
        toolBarRight.setOnClickListener(listener);
    }


    /**
     * 设置标题的颜色
     *
     * @param color 颜色资源文件
     */
    public void setTitleTextColor(@ColorRes int color) {
        titleTextView.setTextColor(getResources().getColor(color));
    }


    /**
     * 设置导航图标，默认是返回键
     *
     * @param icon 图标的资源id
     */
    public void setNavigationIcon(@DrawableRes int icon) {
        toolbar.setNavigationIcon(icon);
    }

    /**
     * 设置导航图标的点击事件
     *
     * @param listener 点击监听器
     */
    public void setNavigationOnClickListener(OnClickListener listener) {
        toolbar.setNavigationOnClickListener(listener);
    }

}

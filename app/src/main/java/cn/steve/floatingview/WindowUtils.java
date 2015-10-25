package cn.steve.floatingview;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import steve.cn.mylib.commonutil.ScreenUtils;


/**
 * Created by Steve on 2015/8/28.
 */
public class WindowUtils {

    private static WindowManager mWindowManager;
    private static Context mContext;
    private static AdvertisementLinearLayout mAdvertisementLinearLayout;
    private static WindowManager.LayoutParams mLayoutParamsSlideBar;
    private static WindowManager.LayoutParams mLayoutParamsCenter;
    private static boolean isShow = false;
    private static int displayWidth = 0;
    private static int displayHeight = 0;
    private static CenterADLinearLayout centerADLinearLayout;
    private static ImageViewOnclickListener imageOnClickListener;
    private static InstallSuccessView installSuccessView;

    /**
     * 弹出Sidebar
     */
    public static void showSidebar(Context context) {
        if (mAdvertisementLinearLayout == null) {
            mAdvertisementLinearLayout = new AdvertisementLinearLayout(context);
        }
        mAdvertisementLinearLayout.setMoveListener(imageOnClickListener);
        //获取WindowManager
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(
            Context.WINDOW_SERVICE);
        //设置LayoutParams(全局变量）相关参数
        mLayoutParamsSlideBar = new WindowManager.LayoutParams();
        // 系统提示类型,重要
        mLayoutParamsSlideBar.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mLayoutParamsSlideBar.format = 1;
        // 不能抢占聚焦点
        mLayoutParamsSlideBar.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParamsSlideBar.flags =
            mLayoutParamsSlideBar.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        // 排版不受限制
        mLayoutParamsSlideBar.flags =
            mLayoutParamsSlideBar.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mLayoutParamsSlideBar.alpha = 1.0f;
        //调整悬浮窗口至右中间部分
        mLayoutParamsSlideBar.gravity = Gravity.RIGHT | Gravity.CENTER;
        //以屏幕左上角为原点，设置x、y初始值
        mLayoutParamsSlideBar.x = 0;
        mLayoutParamsSlideBar.y = 0;
        //设置悬浮窗口长宽数据
        mLayoutParamsSlideBar.width = 8 * displayWidth / 10;
        mLayoutParamsSlideBar.height = 7 * displayHeight / 10;
        mWindowManager.addView(mAdvertisementLinearLayout, mLayoutParamsSlideBar);
        isShow = true;
    }


    //判断侧边栏是否正在显示
    public static boolean isSlideBarShow() {
        return mAdvertisementLinearLayout != null;
    }


    /**
     * 隐藏Sidebar
     */
    public static void hideSidebar(Context context) {
        isShow = false;
        if (mAdvertisementLinearLayout != null) {
            mWindowManager.removeViewImmediate(mAdvertisementLinearLayout);
            mAdvertisementLinearLayout = null;
        }
    }


    /**
     * 显示中心区域的view
     */
    public static void ShowCenterAD(Context context) {
        if (centerADLinearLayout == null) {
            centerADLinearLayout = new CenterADLinearLayout(context);
        }
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(
            Context.WINDOW_SERVICE);
        //设置LayoutParams(全局变量）相关参数
        mLayoutParamsCenter = new WindowManager.LayoutParams();
        // 系统提示类型,重要
        mLayoutParamsCenter.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mLayoutParamsCenter.format = 1;
        // 不能抢占聚焦点
        mLayoutParamsCenter.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParamsCenter.flags =
            mLayoutParamsCenter.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        // 排版不受限制
        mLayoutParamsCenter.flags =
            mLayoutParamsCenter.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mLayoutParamsCenter.alpha = 1.0f;
        //调整悬浮窗口至右中间部分
        mLayoutParamsCenter.gravity = Gravity.CENTER;
        //以屏幕左上角为原点，设置x、y初始值
        mLayoutParamsCenter.x = 0;
        mLayoutParamsCenter.y = 0;
        //设置悬浮窗口长宽数据
        mLayoutParamsCenter.width = 9 * displayWidth / 10;
        mLayoutParamsCenter.height = 8 * displayHeight / 10;
        mWindowManager.addView(centerADLinearLayout, mLayoutParamsCenter);
    }

    /**
     * 判断中心区域的是否正在显示
     */
    public static boolean isCenterShow() {
        return centerADLinearLayout != null;
    }

    /**
     * 隐藏中心区域的view
     */
    public static void hideCenter(Context context) {
        isShow = false;
        if (centerADLinearLayout != null) {
            mWindowManager.removeViewImmediate(centerADLinearLayout);
            centerADLinearLayout = null;
        }
    }

    public static void loadSlideBarURL(String url) {
        if (mAdvertisementLinearLayout != null) {
            mAdvertisementLinearLayout.loadURL(url);
        }
    }

    public static void loadCenterURL(String url) {
        if (centerADLinearLayout != null) {
            centerADLinearLayout.loadURL(url);
        }
    }


    public static void initView(Context context) {
        mContext = context;
        displayWidth = ScreenUtils.getScreenWidth(context);
        displayHeight = ScreenUtils.getScreenHeight(context);
        imageOnClickListener = new ImageViewOnclickListener();
    }

    public static void setImageViewIconBitmap(BitmapDrawable bitmapDrawable) {
        centerADLinearLayout.setImageViewIconBitmap(bitmapDrawable);
    }

    public static void setTextViewAppName(String appName) {
        centerADLinearLayout.setTextViewAppName(appName);
    }

    public static void setTextViewAppPopular(String appPopular) {
        centerADLinearLayout.setTextViewAppPopular(appPopular);
    }

    public static void setTextViewAppDesc(String appDesc) {
        centerADLinearLayout.setTextViewAppDesc(appDesc);
    }

    public static void setImageViewSafe(BitmapDrawable bitmapDrawable) {
        centerADLinearLayout.setImageViewSafe(bitmapDrawable);
    }

    public static InstallSuccessView showInstallSuccessView(String url) {
        if (installSuccessView != null) {
            installSuccessView = null;
        } else {
            installSuccessView = new InstallSuccessView(mContext);
            LinearLayout.LayoutParams
                params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                              ViewGroup.LayoutParams.MATCH_PARENT);
            installSuccessView.setLayoutParams(params);
        }
        return installSuccessView;


    }

    private static class ImageViewOnclickListener
        implements AdvertisementLinearLayout.MoveListener {

        @Override
        public void onClick(int deltawidth) {
            if (mAdvertisementLinearLayout != null) {
                if (isShow) {
                    //更新整个window的位置,向右滑进
                    mLayoutParamsSlideBar.x = mLayoutParamsSlideBar.x - deltawidth;
                    mWindowManager
                        .updateViewLayout(mAdvertisementLinearLayout, mLayoutParamsSlideBar);
                } else {
                    //更新整个window的位置，向左滑出
                    mLayoutParamsSlideBar.x = mLayoutParamsSlideBar.x + deltawidth;
                    mWindowManager
                        .updateViewLayout(mAdvertisementLinearLayout, mLayoutParamsSlideBar);
                }
            }
        }

        @Override
        public void setShowingStatus() {
            if (mLayoutParamsSlideBar.x < 0) {
                isShow = false;
            } else {
                isShow = true;
            }
        }
    }
}


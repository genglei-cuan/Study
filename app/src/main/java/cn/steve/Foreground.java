package cn.steve;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * 整个应用中的activity的生命周期的回调，借此可以判断有几个activity和是否还在前台运行
 *
 * Created by yantinggeng on 2015/10/23.
 */


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Foreground implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = Foreground.class.getName();
    public static final long CHECK_DELAY = 2000;
    public static ForeBackStateChangedListener listener;
    public static Activity current;
    private static Foreground instance;
    private static boolean foreground;
    private Handler handler = new Handler();
    private Runnable check;

    public static Foreground init(Application application) {
        if (instance == null) {
            instance = new Foreground();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static Foreground get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static Foreground get() {
        if (instance == null) {
            throw new IllegalStateException(
                "Foreground is not initialised - first invocation must use parameterised init/get");
        }
        return instance;
    }

    public static boolean isForeground() {
        return foreground;
    }

    public static boolean isBackground() {
        return !foreground;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        current = activity;
        //撤销已经存在的检查，此刻已经是重新启动了一个activity
        if (check != null) {
            handler.removeCallbacks(check);
        }
        //判断原先不在前台运行，并且没有旋转屏幕,表示切换到了前台
        if (!foreground && (activity != null && !activity.isChangingConfigurations())) {
            foreground = true;
            listener.onBecameForeground();
            System.out.println("became foreground");
        }
        //否则表示原先就在前台运行
        else {
            System.out.println("still foreground");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        //全部的检查放到pause中
        if (check != null) {
            handler.removeCallbacks(check);
        }
        //只要不是旋转屏幕的原因,就进行检查是否已经退出了APP
        if (!activity.isChangingConfigurations()) {
            final WeakReference<Activity> ref = new WeakReference<>(activity);
            handler.postDelayed(check = new Runnable() {
                @Override
                public void run() {
                    onActivityCeased(ref.get());
                }
            }, CHECK_DELAY);
        }

    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        //移除检查
//        if (check != null) {
//            handler.removeCallbacks(check);
//        }
//        onActivityCeased(activity);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    //当有activity停止的时候进行调用
    private void onActivityCeased(Activity activity) {
        //原先在前台
        if (foreground) {
            //停止的activity是最后一次显示的activity，并且当前这个activity不是由于旋转屏幕引起的停止
            if ((activity == current) && (activity != null && !activity
                .isChangingConfigurations())) {
                foreground = false;
                listener.onBecameBackground();
                System.out.println("went background");
            }
            //否则表示已经在前台
            else {
                System.out.println("still foreground");
            }
        }
        //依旧在后台
        else {
            System.out.println("still background");
        }
    }

    //监听器,用作APP可见和不可见的时候的回调
    public interface ForeBackStateChangedListener {

        void onBecameForeground();

        void onBecameBackground();
    }

}

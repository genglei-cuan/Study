package cn.steve;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 整个应用中的activity的生命周期的回调，借此可以判断有几个activity和是否还在前台运行， 借鉴了网上的一个demo，实现前后台切换时候的回调
 *
 * https://github.com/steveliles/Foredroid
 *
 * Created by yantinggeng on 2015/10/23.
 */


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Foreground implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = Foreground.class.getName();
    public static final long CHECK_DELAY = 2000;

    private static Foreground instance;

    private static Callback becameForeground = new Callback() {
        @Override
        public void invoke(Listener listener) {
            listener.onBecameForeground();
        }
    };
    private static Callback becameBackground = new Callback() {
        @Override
        public void invoke(Listener listener) {
            listener.onBecameBackground();
        }
    };

    private boolean foreground;
    private Activity current;
    private Listeners listeners = new Listeners();
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

    public boolean isForeground() {
        return foreground;
    }

    public boolean isBackground() {
        return !foreground;
    }

    public Binding addListener(Listener listener) {
        return listeners.add(listener);
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // if we're changing configurations we aren't going background so
        // no need to schedule the check
        if (!activity.isChangingConfigurations()) {
            // don't prevent activity being gc'd
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
        current = activity;
        if (check != null) {
            handler.removeCallbacks(check);
        }
        //判断原先不在前台运行，并且没有旋转屏幕,表示切换到了前台
        if (!foreground && (activity != null && !activity.isChangingConfigurations())) {
            foreground = true;
            Log.w(TAG, "became foreground");
            listeners.each(becameForeground);
        }
        //否则表示原先就在前台运行
        else {
            Log.i(TAG, "still foreground");
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        //移除检查
        if (check != null) {
            handler.removeCallbacks(check);
        }
        onActivityCeased(activity);
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
                Log.w(TAG, "went background");
                listeners.each(becameBackground);
            }
            //否则表示已经在前台
            else {
                Log.i(TAG, "still foreground");
            }
        }
        //依旧在后台
        else {
            Log.i(TAG, "still background");
        }
    }

    public interface Listener {

        public void onBecameForeground();

        public void onBecameBackground();
    }

    public interface Binding {

        void unbind();
    }

    private interface Callback {

        void invoke(Listener listener);
    }

    private static class Listeners {

        private List<WeakReference<Listener>> listeners = new CopyOnWriteArrayList<>();

        public Binding add(Listener listener) {
            final WeakReference<Listener> wr = new WeakReference<>(listener);
            listeners.add(wr);
            return new Binding() {
                public void unbind() {
                    listeners.remove(wr);
                }
            };
        }

        public void each(Callback callback) {
            for (Iterator<WeakReference<Listener>> it = listeners.iterator(); it.hasNext(); ) {
                try {
                    WeakReference<Listener> wr = it.next();
                    Listener l = wr.get();
                    if (l != null) {
                        callback.invoke(l);
                    } else {
                        it.remove();
                    }
                } catch (Exception exc) {
                    Log.e(TAG, "Listener threw exception!", exc);
                }
            }
        }
    }
}

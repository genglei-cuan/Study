package steve.cn.mylib.log;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * 配置一个漂亮的log显示
 */
public class SteveLog {

    public final static String TAG = "steveTag";

    public static void log(String s) {
        Log.i(TAG, s);
    }

    /**
     * 只有log信息和线程信息，没有方法信息
     *
     * @param d message
     */
    public static void dNoMethod(String d) {
        Logger.init("SteveLog").methodCount(0);
        Logger.d(d);
    }

    /**
     * 只有log信息和方法信息，没有线程信息
     *
     * @param d message
     */
    public static void dNoThread(String d) {
        Logger.init("SteveLog").hideThreadInfo();
        Logger.d(d);
    }

    /**
     * 只有log信息
     *
     * @param d message
     */
    public static void d(String d) {
        Logger.init("SteveLog").hideThreadInfo().methodCount(0);
        Logger.d(d);
    }

    public static void dAll(String d) {
        Logger.d("", d);
    }
}

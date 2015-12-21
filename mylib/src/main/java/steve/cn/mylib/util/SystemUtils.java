package steve.cn.mylib.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by Steve on 2015/8/28.
 */
public class SystemUtils {

    /**
     * recommend default thread pool size according to system available processors, {@link
     * #getDefaultThreadPoolSize()}
     **/
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

    private SystemUtils() {
        throw new AssertionError();
    }

    //获得系统的堆栈的大小，结果是以MB为单位进行返回
    public static int getHeapSize(Context context) {
        ActivityManager manager =
            (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        return heapSize;
    }

    /**
     * get recommend default thread pool size
     *
     * @return if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * get recommend default thread pool size
     *
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }


    /**
     * 获取CPU的指令集
     */
    public static String getABIS() {
        if (Build.VERSION.SDK_INT < 21) {
            return Build.CPU_ABI + " and " + Build.CPU_ABI2;
        } else {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < supportedAbis.length; i++) {
                s.append(supportedAbis[i]);
            }
            return s.toString();
        }
    }


}

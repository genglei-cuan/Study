package steve.cn.mylib.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Steve on 2015/8/27.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //这个操作能有效地释放系统的资源
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            /**
             只有在整个程序都不可见的时候才会到达的级别(是整个程序)
             onTrimMemory(TRIM_MEMORY_UI_HIDDEN)这个回调之后才去释放，
             这样可以保证如果用户只是从我们程序的一个Activity回到了另外一个Activity，
             界面相关的资源都不需要重新加载，从而提升响应速度。
             */
            case TRIM_MEMORY_UI_HIDDEN:
                // 进行资源释放操作
                break;
/**
 *表示应用程序正常运行，并且不会被杀掉。
 * 但是目前手机的内存已经有点低了，
 * 系统可能会开始根据LRU缓存规则来去杀死进程了。
 */
            case TRIM_MEMORY_RUNNING_MODERATE:
                break;
/**
 *表示应用程序正常运行，并且不会被杀掉。
 * 但是目前手机的内存已经非常低了，
 * 我们应该去释放掉一些不必要的资源以提升系统的性能，
 * 同时这也会直接影响到我们应用程序的性能。
 *
 */
            case TRIM_MEMORY_RUNNING_LOW:
                break;
/**
 *表示应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。
 * 这个时候我们应当尽可能地去释放任何不必要的资源，不然的话系统可能会继续杀掉所有缓存中的进程，
 * 并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务。
 *
 */
            case TRIM_MEMORY_RUNNING_CRITICAL:
                break;
            //如果我们的程序目前是被缓存的，则会收到以下几种类型的回
/**
 *表示手机目前内存已经很低了，系统准备开始根据LRU缓存来清理进程。
 * 这个时候我们的程序在LRU缓存列表的最近位置，是不太可能被清理掉的，但这时去释放掉一些比较容易恢复的资源能够让手机的内存变得比较充足，
 * 从而让我们的程序更长时间地保留在缓存当中， 这样当用户返回我们的程序时会感觉非常顺畅，而不是经历了一次重新启动的过程。
 *
 */
            case TRIM_MEMORY_BACKGROUND:
                break;
/**
 *表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，
 * 如果手机内存还得不到进一步释放的话，那么我们的程序就有被系统杀掉的风险了。
 *
 */
            case TRIM_MEMORY_MODERATE:
                break;
/**
 *
 * 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，系统会最优先考虑杀掉我们的应用程序，
 * 在这个时候应当尽可能地把一切可以释放的东西都进行释放。
 */
            case TRIM_MEMORY_COMPLETE:
                break;

        }
    }
}

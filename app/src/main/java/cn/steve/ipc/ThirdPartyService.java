package cn.steve.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * date 2016/6/3
 *
 * by yantinggeng
 *
 * Description : 测试独立进程启动服务获取信息
 */
public class ThirdPartyService extends Service {

    public static final String TAG = "ThirdPartyService";

    public ThirdPartyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.i(TAG, "run: Hello i'm running");
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: Hello i'm dead");
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}

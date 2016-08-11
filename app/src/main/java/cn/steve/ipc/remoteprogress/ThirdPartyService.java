package cn.steve.ipc.remoteprogress;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Process;
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
        SharedPreferences sp = this.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        boolean success = editor.putString("ThirdUtil", "Hello world!").commit();
        //if (success) {
        //    android.os.Process.killProcess(android.os.Process.myPid());
        //}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: Hello i'm dead");
        Process.killProcess(Process.myPid());
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}

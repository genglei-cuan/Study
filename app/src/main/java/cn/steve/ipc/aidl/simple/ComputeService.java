package cn.steve.ipc.aidl.simple;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by yantinggeng on 2016/8/4.
 */
public class ComputeService extends Service {

    private Binder mBinder = new IComputeIml();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


}

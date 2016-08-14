package cn.steve.ipc.aidl.simple;

import android.os.RemoteException;

import cn.steve.ipc.aidl.ICompute;

/**
 * Created by yantinggeng on 2016/8/1.
 */
public class IComputeIml extends ICompute.Stub {

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}

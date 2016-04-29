package cn.steve.agera;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by yantinggeng on 2016/4/29.
 */
public class NetworkCallingSupplier implements Supplier<Result<String>> {

    private static final String TAG = "NetworkCallingSupplier";

    @NonNull
    @Override
    public Result<String> get() {
        try {
            Log.d(TAG, "get() called with: " + Thread.currentThread().getName());
            Thread.sleep(3000);
            String blob = "Success";
            return Result.success(blob);
        } catch (Throwable e) {
            return Result.failure(e);
        }
    }
}
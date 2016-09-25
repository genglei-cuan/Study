package com.steve.advanced.okhttp;

import android.support.v4.util.ArrayMap;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by yantinggeng on 2016/9/23.
 */

public class OkHttpProvider {

    private static OkHttpProvider provider = null;
    private ArrayMap<String, MyHttpCallBack> taskCallBack = new ArrayMap<>();
    private ArrayMap<String, Call> taskCall = new ArrayMap<>();
    private OkHttpClient client;

    private OkHttpProvider() {
        client = new OkHttpClient();
    }

    public static OkHttpProvider getInstance() {
        if (provider == null) {
            provider = new OkHttpProvider();
        }
        return provider;
    }

    public void get(String url, final String key, final MyHttpCallBack myHttpCallBack) {
        Call call1 = taskCall.get(key);
        if (call1 != null) {
            call1.cancel();
        }
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        taskCallBack.put(key, myHttpCallBack);
        taskCall.put(key, call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyHttpCallBack callback = taskCallBack.get(key);
                if (callback != null) {
                    if (!callback.equals(myHttpCallBack)) {
                        call.cancel();
                        return;
                    }
                    myHttpCallBack.onFailure();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyHttpCallBack callback = taskCallBack.get(key);
                if (callback != null) {
                    if (!callback.equals(myHttpCallBack)) {
                        call.cancel();
                        return;
                    }
                    ResponseBody body = response.body();
                    myHttpCallBack.onResponse(body.toString());
                    try {
                        body.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}

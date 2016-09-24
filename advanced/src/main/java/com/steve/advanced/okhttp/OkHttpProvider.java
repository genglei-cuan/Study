package com.steve.advanced.okhttp;

import android.support.v4.util.ArrayMap;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yantinggeng on 2016/9/23.
 */

public class OkHttpProvider {

    private static OkHttpProvider provider = null;
    private ArrayMap<String, MyHttpCallBack> task = new ArrayMap<>();
    private OkHttpClient client;

    private OkHttpProvider() {
        client = new OkHttpClient();
    }

    public OkHttpProvider getInstance() {
        if (provider == null) {
            provider = new OkHttpProvider();
        }
        return provider;
    }

    public void get(String url, final String key, final MyHttpCallBack callBack) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        task.put(key, callBack);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!task.get(key).equals(callBack)) {
                    call.cancel();
                    return;
                }
                callBack.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!task.get(key).equals(callBack)) {
                    call.cancel();
                    return;
                }
                callBack.onResponse(response.toString());
            }
        });
    }


}

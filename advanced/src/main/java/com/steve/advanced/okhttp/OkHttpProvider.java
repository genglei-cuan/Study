package com.steve.advanced.okhttp;

import android.support.v4.util.ArrayMap;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yantinggeng on 2016/9/23.
 */

public class OkHttpProvider {

    private static OkHttpProvider provider = null;
    private ArrayMap<String, Call> task = new ArrayMap<>();
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

    public String get(String url, String key) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        if (task.containsKey(key)) {
            task.get(key).cancel();
            task.put(key, call);
        }
        Response response = call.execute();
        return response.body().string();
    }

}

package com.steve.advanced.okhttp;

/**
 * Created by steveyan on 16-9-24.
 */

public interface MyHttpCallBack {

    void onFailure();

    void onResponse(String response);
}
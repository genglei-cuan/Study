package cn.steve.gson;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by yantinggeng on 2015/10/16.
 */
public class WeatherRestClient {

    private static final String BASE_URL = "http://apis.baidu.com/heweather/weather/free";
    private static AsyncHttpClient client;

    static {
        client = new AsyncHttpClient();
        client.addHeader("apikey", "9bf616e5be3bf5e85a4c30d8fc6e9736");
    }

    //get 请求
    public static void get(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(BASE_URL, params, responseHandler);
    }

    //post 请求
    public static void post(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(BASE_URL, params, responseHandler);
    }


}

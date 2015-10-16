package cn.steve.gson;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by yantinggeng on 2015/10/16.
 */
public class WeatherRestClient {

    private static final String BASE_URL = "http://apis.baidu.com/heweather/weather/free";

    private static AsyncHttpClient client = new AsyncHttpClient();

    //get 请求
    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    //post 请求
    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }


}

package cn.steve.gson;

import android.app.Activity;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import steve.cn.mylib.util.DialogUtil;

/**
 * 统一对外的方法
 *
 * Created by yantinggeng on 2015/10/29.
 */
public class WeatherUtil {


    public static void getWeather(Activity activity, String city) {
        final DialogUtil dialogUtil = new DialogUtil(activity);
        RequestParams requestParams = new RequestParams();
        requestParams.put("city", city);
        //get请求
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                dialogUtil.showLoadingDialog();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                dialogUtil.cancelLoadingDialog();
                if (statusCode == 200) {
                    //解析返回的json数据，由于返回的数据中key含有空格，故而比较麻烦
                    String s = new String(response);
                    onFinished(s);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse,
                                  Throwable e) {
                dialogUtil.cancelLoadingDialog();
                String s = new String(errorResponse);
                System.out.println("失败：" + s);
            }

            @Override
            public void onRetry(int retryNo) {
            }
        };
        WeatherRestClient.get(requestParams, responseHandler);
    }

    //当成功获取了数据之后
    public static void onFinished(String s) {
        try {
            JSONObject completeObject = new JSONObject(s);
            JSONArray content =
                completeObject.getJSONArray("HeWeather data service 3.0");
            JSONObject trueWeatherData = content.getJSONObject(0);
            Gson gson = new Gson();
            WeatherData data =
                gson.fromJson(trueWeatherData.toString(), WeatherData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

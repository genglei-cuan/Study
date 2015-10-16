package cn.steve.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/12.
 */
public class GsonMainActivity extends AppCompatActivity {

    public static final String httpUrl = "http://apis.baidu.com/heweather/weather/free";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        this.listView = (ListView) findViewById(android.R.id.list);
        request();

    }


    private void request() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("apikey", " 9bf616e5be3bf5e85a4c30d8fc6e9736");
        RequestParams requestParams = new RequestParams();
        requestParams.put("city", "beijing");

        //get请求
        client.get(httpUrl, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                if (statusCode == 200) {
                    String s = new String(response);
                    System.out.println("成功:" + s);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse,
                                  Throwable e) {
                String s = new String(errorResponse);
            }

            @Override
            public void onRetry(int retryNo) {
            }
        });
    }


}

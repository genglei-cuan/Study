package cn.steve.http.asynchttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/9/30.
 */
public class AsyncHttpActivity extends AppCompatActivity {

    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);

        AsyncHttpClient client = new AsyncHttpClient();

        //get请求
        client.get("https://www.baidu.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                if (statusCode == 200) {
                    textViewMain.setText(String.valueOf(response));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse,
                                  Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                AsyncHttpActivity.this.textViewMain.setText(String.valueOf(statusCode));
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

        //post请求
        RequestParams params = new RequestParams();
        params.put("key", "value");
        params.put("more", "data");


    }
}

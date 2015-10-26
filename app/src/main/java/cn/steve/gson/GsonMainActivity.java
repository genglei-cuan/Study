package cn.steve.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import cn.steve.study.R;
import steve.cn.mylib.util.DialogUtil;

/**
 * Created by yantinggeng on 2015/10/12.
 */
public class GsonMainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        this.listView = (ListView) findViewById(android.R.id.list);
        request();
    }

    private void request() {
        final DialogUtil dialogUtil = new DialogUtil(GsonMainActivity.this);
        RequestParams requestParams = new RequestParams();
        requestParams.put("city", "beijing");
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
                    String s = new String(response);
                    System.out.println("成功:" + s);
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


}

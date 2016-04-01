package cn.steve.http.asynchttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cn.steve.study.R;
import cz.msebera.android.httpclient.Header;

/**
 * Created by yantinggeng on 2015/9/30.
 */
public class AsyncHttpActivity extends AppCompatActivity {

  private static final String GETURL = "https://raw.github.com/square/okhttp/master/README.md";
  AsyncHttpClient client = new AsyncHttpClient();
  private TextView textViewMain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_textview);
    this.textViewMain = (TextView) findViewById(R.id.textViewMain);

    //get请求
    getMethod();

    //post请求
    RequestParams params = new RequestParams();
    params.put("key", "value");
    params.put("more", "data");


  }

  private void getMethod() {
    client.get(GETURL, new AsyncHttpResponseHandler() {
      @Override
      public void onStart() {
        // called before request is started
      }

      @Override
      public void onSuccess(int statusCode, Header[] headers, byte[] response) {
        // called when response HTTP status is "200 OK"
        if (statusCode == 200) {
          String s = new String(response);
          textViewMain.setText(s);
        }
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, byte[] errorResponse,
                            Throwable e) {
        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("statusCode:").append(statusCode);
        if (errorResponse != null) {
          String s = new String(errorResponse);
          stringBuffer.append(s);
        }
        textViewMain.setText(stringBuffer.toString());
      }

      @Override
      public void onRetry(int retryNo) {
        // called when request is retried
      }
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.swiperefreshlayout, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    getMethod();
    return true;
  }
}

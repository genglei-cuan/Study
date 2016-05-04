package cn.steve.http.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import cn.steve.study.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yantinggeng on 2015/9/30.
 */
public class OkHttpActivity extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String GETURL = "https://raw.github.com/square/okhttp/master/README.md";
    private static final String POSTURL = "http://www.roundsapp.com/post";
    private android.widget.TextView textViewMain;
    private OkHttpClient client;

    //网络请求不能放在主线程当中
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    textViewMain.setText("getMsg" + msg.obj.toString());
                    break;
                case 2:
                    Log.i("postMsg:", msg.obj.toString());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        client = new OkHttpClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //json
                String json = bowlingJson("Jesse", "Jake");
                try {
                    String postMes = post(POSTURL, json);
                    String getMes = get(GETURL);

                    Message msgGet = mHandler.obtainMessage(1);
                    msgGet.obj = getMes;
                    msgGet.sendToTarget();
                    Message msgPost = mHandler.obtainMessage(2);
                    msgPost.obj = postMes;
                    msgPost.sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    //get请求
    private String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //post请求
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //包装请求体中的json数据
    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE'," + "'name':'Bowling'," + "'round':4,"
               + "'lastSaved':1367702411696," + "'dateStarted':1367702378785,"
               + "'players':[" + "{'name':'" + player1
               + "','history':[10,8,6,7,8],'color':-13388315,'total':39}," + "{'name':'" + player2
               + "','history':[6,10,5,10,10],'color':-48060,'total':41}" + "]}";
    }


}

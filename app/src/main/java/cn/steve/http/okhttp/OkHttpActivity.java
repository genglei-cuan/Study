package cn.steve.http.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/9/30.
 */
public class OkHttpActivity extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String GETURL = "https://raw.github.com/square/okhttp/master/README.md";
    private static final String POSTURL = "http://www.roundsapp.com/post";
    private android.widget.TextView textViewMain;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        client = new OkHttpClient();
        try {
            textViewMain.setText(get(GETURL));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //json
        String json = bowlingJson("Jesse", "Jake");
        try {
            System.out.println(post(POSTURL, json));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
               + "'name':'Bowling',"
               + "'round':4,"
               + "'lastSaved':1367702411696,"
               + "'dateStarted':1367702378785,"
               + "'players':["
               + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
               + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
               + "]}";
    }


}

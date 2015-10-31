package cn.steve.newsImoocListView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.steve.study.R;


public class NewsImoocActivity extends ActionBarActivity {

    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imooc_main);
        mListView = (ListView) findViewById(R.id.lv_main);
        new NewsAsyncTask().execute(URL);

    }


    //将URL对应的json格式数据转换成我们所封装的newsbean对象
    private List<NewsBean> getJsonData(String url) throws JSONException {
        List<NewsBean> newsBeansList = new ArrayList<NewsBean>();
        String jsonString = null;
        try {
            jsonString = readStream(new URL(url).openStream());
            Log.i("steve", jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(jsonString);
        NewsBean newsBean;
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            newsBean = new NewsBean();
            newsBean.newsIconUrl = jsonObject.getString("picSmall");
            newsBean.newsTitle = jsonObject.getString("name");
            newsBean.newsContent = jsonObject.getString("description");
            newsBeansList.add(newsBean);
        }
        return newsBeansList;
    }

    //通过inputstream解析网页返回的数据
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //实现网络的异步访问
    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {

        @Override
        protected List<NewsBean> doInBackground(String... params) {
            List<NewsBean> list = null;
            try {
                list = getJsonData(params[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            NewsAdapter adapter = new NewsAdapter(NewsImoocActivity.this, newsBeans, mListView);
            mListView.setAdapter(adapter);
        }
    }

}

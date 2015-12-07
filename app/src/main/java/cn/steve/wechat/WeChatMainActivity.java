package cn.steve.wechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/7.
 */
public class WeChatMainActivity extends AppCompatActivity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        listView = (ListView) findViewById(R.id.weChatListView);
        initData();
    }


    private void initData() {
        ArrayList<WeChatModel> data = new ArrayList<>();
        WeChatModel model = null;
        for (int i = 0; i < 10; i++) {
            model = new WeChatModel();
            model.setTextContent("I AM " + i);
            model.setType(i % 2);
            data.add(model);
        }
        WeChatAdapter adapter = new WeChatAdapter(this, data);
        listView.setAdapter(adapter);
    }


}

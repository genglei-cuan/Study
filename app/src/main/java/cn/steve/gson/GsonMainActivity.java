package cn.steve.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import cn.steve.study.R;

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
    }



}

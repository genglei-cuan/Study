package cn.steve.listview.customitemheight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/3.
 */
public class CustomItemHeightActivity extends AppCompatActivity {


    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customlistview);
        this.list = (ListView) findViewById(android.R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            datas.add("SteveYan" + i);
        }
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, datas);
        list.setAdapter(adapter);
    }
}

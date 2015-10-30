package cn.steve.swiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * SwipeRefreshLayout由系统自带的兼容包中的下拉刷新的控件
 *
 *
 * Created by yantinggeng on 2015/10/30.
 */
public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    private android.widget.ListView mListView;
    private android.support.v4.widget.SwipeRefreshLayout swiperefresh;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefreshlayout);
        this.swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        this.mListView = (ListView) findViewById(android.R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
        mListView.setAdapter(adapter);

        this.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myUpdateOperation();
            }
        });
    }

    //下拉刷新的时候执行的操作
    private void myUpdateOperation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("Add");
                adapter.notifyDataSetChanged();
                // 停止刷新
                swiperefresh.setRefreshing(false);
            }
        }, 5000); // 5秒后发送消息，停止刷新
    }

    private ArrayList<String> getData() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.swiperefreshlayout, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                //设置成刷新状态
                swiperefresh.setRefreshing(true);
                //执行刷新需要执行的耗时操作，下拉才会执行的操作
                myUpdateOperation();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

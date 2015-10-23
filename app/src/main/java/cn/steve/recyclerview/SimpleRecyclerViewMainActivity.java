package cn.steve.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import cn.steve.study.R;


/**
 * Created by Steve on 2015/8/19.
 */
public class SimpleRecyclerViewMainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private SimpleRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //创建默认的线性LayoutManager ;设置成横向的,默认为竖屏的
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //设置表格状的
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
//        mRecyclerView.setLayoutManager(gridLayoutManager);

        //瀑布流
//        StaggeredGridLayoutManager
//            mStaggeredGridLayoutManager =
//            new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        mAdapter = new SimpleRecyclerAdapter(getDummyDatas());
        mAdapter.setOnItemClickListener(
                new SimpleRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, String data) {
                        //Do fucking source
                        Toast.makeText(SimpleRecyclerViewMainActivity.this, data, Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        mRecyclerView.setAdapter(mAdapter);
    }

    private String[] getDummyDatas() {
        String[] datas = new String[200];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = "Data" + i;
        }
        return datas;
    }
}

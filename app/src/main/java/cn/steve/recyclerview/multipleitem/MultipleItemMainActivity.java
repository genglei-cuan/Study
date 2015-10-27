package cn.steve.recyclerview.multipleitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.steve.study.R;

/**
 * 验证ItemViewType写的demo；
 *
 * 根据getItemViewType的值的不同返回不用的viewholder
 *
 *
 * Created by yantinggeng on 2015/10/27.
 */
public class MultipleItemMainActivity extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView myrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        this.myrecyclerview = (RecyclerView) findViewById(R.id.my_recycler_view);
        this.myrecyclerview.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于list view
        MultipleItemRecycleAdapter
            multipleItemRecycleAdapter =
            new MultipleItemRecycleAdapter(this, getDummyDatas());
        this.myrecyclerview.setAdapter(multipleItemRecycleAdapter);
    }


    private String[] getDummyDatas() {
        String[] datas = new String[200];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = "Data" + i;
        }
        return datas;
    }
}

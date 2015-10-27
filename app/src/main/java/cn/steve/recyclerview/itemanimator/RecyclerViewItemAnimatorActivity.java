package cn.steve.recyclerview.itemanimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/27.
 */
public class RecyclerViewItemAnimatorActivity extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView myrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        this.myrecyclerview = (RecyclerView) findViewById(R.id.my_recycler_view);


    }
}

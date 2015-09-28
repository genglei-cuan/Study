package cn.steve.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
 * 一个单例内部维持着一个map对象存储了一堆的方法；post无非就是根据参数去查找方法，进行反射调用。
 *
 * Created by yantinggeng on 2015/9/25.
 */
public class EventBusActivity extends AppCompatActivity implements
                                                        ItemListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
    }

    @Override
    public void onFragmentInteraction(String id) {
    }
}


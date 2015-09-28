package cn.steve.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
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


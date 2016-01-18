package cn.steve.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import cn.steve.study.R;
import steve.cn.mylib.viewpager.CircleIndicator;

/**
 * Created by yantinggeng on 2016/1/18.
 */
public class ViewPagerMainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_default);

        for (int i = 0; i < 10; i++) {
            data.add("Page" + i);
        }
        this.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),data));
        indicator.setViewPager(viewPager);
    }
}


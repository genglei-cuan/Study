package cn.steve.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import cn.steve.study.R;
import steve.cn.mylib.viewpager.CircleIndicator;

/**
 * Created by yantinggeng on 2016/8/16.
 */
public class LazyFragmentActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator indicatordefault;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        this.indicatordefault = (CircleIndicator) findViewById(R.id.indicator_default);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_default);
        ArrayList<Fragment> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new CustomLazyFragment());
        }
        if (viewPager != null) {
            viewPager.setAdapter(new LazyAdapter(getSupportFragmentManager(), data));
        }
        if (indicator != null) {
            indicator.setViewPager(viewPager);
        }
    }
}

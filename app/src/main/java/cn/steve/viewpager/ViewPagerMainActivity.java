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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_default);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("Page" + i);
        }
        if (viewPager != null) {
            viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), data));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        if (indicator != null) {
            indicator.setViewPager(viewPager);
        }


    }
}


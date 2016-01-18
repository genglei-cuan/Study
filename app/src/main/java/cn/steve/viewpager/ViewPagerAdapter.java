package cn.steve.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by yantinggeng on 2016/1/18.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> data = null;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<String> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return RecommendFragment.newInstance("" + position, data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }
}

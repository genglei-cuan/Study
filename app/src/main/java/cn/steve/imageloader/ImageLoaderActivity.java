package cn.steve.imageloader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import cn.steve.imageloader.fragment.ImageGridFragment;
import cn.steve.imageloader.fragment.ImageListFragment;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/9/22.
 */
public class ImageLoaderActivity extends AppCompatActivity {

    private static final String STATE_POSITION = "STATE_POSITION";

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uil);
        int pagerPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(STATE_POSITION);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, pager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentPagerAdapter {

        Fragment listFragment;
        Fragment gridFragment;

        ImagePagerAdapter(FragmentManager fm) {
            super(fm);
            listFragment = new ImageListFragment();
            gridFragment = new ImageGridFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return listFragment;
                case 1:
                    return gridFragment;
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "list";
                case 1:
                    return "grid";
                default:
                    return null;
            }
        }
    }

}

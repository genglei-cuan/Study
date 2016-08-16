package cn.steve.viewpager;

import android.view.View;

import cn.steve.study.R;
import steve.cn.mylib.viewpager.LazyLoadFragment;

/**
 * Created by yantinggeng on 2016/8/16.
 */
public class CustomLazyFragment extends LazyLoadFragment {

    @Override
    public int getLayout() {
        return R.layout.activity_main_textview;
    }

    @Override
    public void initViews(View view) {

    }
}

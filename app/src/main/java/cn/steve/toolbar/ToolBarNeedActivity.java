package cn.steve.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/15.
 */
public class ToolBarNeedActivity extends ToolBarBaseActivity {

    @Override
    protected View getContentView(ViewGroup parent) {
        return LayoutInflater.from(this).inflate(R.layout.article_fra1, parent, false);
    }
}

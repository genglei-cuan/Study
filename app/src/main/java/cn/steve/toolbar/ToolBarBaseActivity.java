package cn.steve.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/15.
 */
public class ToolBarBaseActivity extends AppCompatActivity {

    ViewGroup rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_toolbar_base, null, false);
        View toolBar = toolBarView();
        if (toolBar != null) {
            rootView.addView(toolBar);
        }
        View contentView = getContentView(rootView);
        if (contentView != null) {
            rootView.addView(contentView);
        }
        setContentView(rootView);
    }

    protected View toolBarView() {
        return LayoutInflater.from(this).inflate(R.layout.toolbar_custom, rootView, false);
    }

    protected View getContentView(ViewGroup parent) {
        return null;
    }
}

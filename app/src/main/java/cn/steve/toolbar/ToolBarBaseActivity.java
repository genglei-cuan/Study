package cn.steve.toolbar;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/6/15.
 */
public class ToolBarBaseActivity extends AppCompatActivity {

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_toolbar_base, null, false);
    View contentView = LayoutInflater.from(this).inflate(layoutResID, rootView, false);
    View toolbarView = LayoutInflater.from(this).inflate(R.layout.toolbar_custom, rootView, false);
    if (toolbarView != null) {
      rootView.addView(toolbarView);
    }
    if (contentView != null) {
      rootView.addView(contentView);
    }
    setContentView(rootView);
  }

  //exposed the toolbar methods
  protected void setToolBar() {

  }
}

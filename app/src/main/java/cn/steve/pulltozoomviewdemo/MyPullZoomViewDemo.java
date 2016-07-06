package cn.steve.pulltozoomviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import cn.steve.study.R;
import steve.cn.mylib.view.PullZoomView;

public class MyPullZoomViewDemo extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_pull_zoom_view_demo);
    PullZoomView pullZoomView = (PullZoomView) findViewById(R.id.pullZoomView);
    ImageView headImageView = (ImageView) findViewById(R.id.headImageView);
  }
}

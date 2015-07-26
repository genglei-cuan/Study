package cn.steve.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import cn.steve.study.R;

/**
 * 添加fragment到界面上 简单的了解
 * 
 * @author steve.yan
 *
 */
public class MyFragmentActivity extends FragmentActivity {
  public FragmentTransaction fragmentTransaction;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.myfragment);
    System.out.println("MyFragmentActivity");
    // 在程序中加入Fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    // 第一个fragment
    ArticleFragment fragment = new ArticleFragment();
    // 添加到activity的布局中
    fragmentTransaction.add(R.id.fragment_container, fragment);
    // 第二个
    ContentFragment content = new ContentFragment();
    fragmentTransaction.add(R.id.fragment_container2, content);
    // 提交数据
    fragmentTransaction.commit();
  }
}

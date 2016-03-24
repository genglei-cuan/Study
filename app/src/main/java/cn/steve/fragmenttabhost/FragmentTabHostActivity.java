package cn.steve.fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TabHost;

import cn.steve.materialdesign.MaterialSimpleFragment;
import cn.steve.study.R;

public class FragmentTabHostActivity extends AppCompatActivity {

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    //定义一个布局
    private LayoutInflater layoutInflater;
    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "消息", "好友", "广场", "更多"};
    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //得到fragment的个数
        for (int i = 0; i < 5; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(mTextviewArray[i]);
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, MaterialSimpleFragment.class, null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.background);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        initView();
    }
}

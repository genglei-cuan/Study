package cn.steve.layoutinflater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.steve.study.R;

/**
 * 解释LayoutInflater的正确含义
 *
 * inflate (XmlPullParser parser, ViewGroup root, boolean attachToRoot)
 *
 * 第一个参数：待加载的xml布局文件；
 *
 * 第二个参数：表示待加载的布局需要被加入到哪个根布局中，当然这个加入依赖于第三个参数；
 *
 * 第三个参数：true表示，加入到第二个参数的布局之中，false，第二参数的布局参数拿出来给xml作为参考系。
 *
 *
 * Created by yantinggeng on 2015/11/3.
 */
public class LayoutInflaterActivity extends AppCompatActivity {

    private View view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutinflater_main);

        ViewGroup parent = (ViewGroup) findViewById(R.id.layoutInflaterContainer);
        // result: layout_height=wrap_content layout_width=match_parent
        view = LayoutInflater.from(this).inflate(R.layout.activity_layoutinflater_sub, null);
        parent.addView(view);

        // result: layout_height=100 layout_width=100
        view = LayoutInflater.from(this).inflate(R.layout.activity_layoutinflater_sub, null);
        parent.addView(view, 100, 100);

        // result: layout_height=25dp layout_width=25dp
        // view=textView due to attachRoot=false
        view = LayoutInflater.from(this).inflate(R.layout.activity_layoutinflater_sub, parent, false);
        parent.addView(view);

        // result: layout_height=25dp layout_width=25dp
        // parent.addView not necessary as this is already done by attachRoot=true
        // view=root due to parent supplied as hierarchy root and attachRoot=true
        view = LayoutInflater.from(this).inflate(R.layout.activity_layoutinflater_sub, parent, true);
    }
}

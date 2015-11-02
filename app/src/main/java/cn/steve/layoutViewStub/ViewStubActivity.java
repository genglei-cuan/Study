package cn.steve.layoutViewStub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.steve.study.R;


/**
 * 1.include 2.ViewStub 3.merge
 * <p/>
 * <p/>
 * 区别： include会将当前的整个布局文件的结构加入到parent当中； merge会将布局当中的所有子元素都加入到parent当中。
 *
 * @author Steve
 */
public class ViewStubActivity extends Activity {

    ListView commonListView;
    private ViewStub mViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewstub);

        mViewStub = (ViewStub) findViewById(R.id.mviewstub);

    }

    public void method1(View v) {
        // 方法一：获取viewstub，通过判断可见性来区别十分可见
        // 加载评论的布局
        mViewStub.setVisibility(View.VISIBLE);
        // 获取评论列表里的listview,对应的是 android:inflatedId，不是viewstub的ID
        commonListView = (ListView) findViewById(R.id.commonlistview);
        String[] sw = new String[100];
        for (int i = 0; i < 100; i++) {
            sw[i] = "listtest_" + i;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sw);// 使用系统已经实现好的xml文件simple_list_item_1
        commonListView.setAdapter(adapter);
        if (mViewStub.getVisibility() == View.VISIBLE) {
            // 表示已经加载可见
        }
    }

    private void method2() {
        // 方法二，通过inflate方法来加载布局
        mViewStub = (ViewStub) findViewById(R.id.mviewstub);
        // 成员变量commonLv为空的时候，就进行加载
        if (commonListView == null) {
            // 加载评论的列表,得到的是被包含的布局文件
            commonListView = (ListView) mViewStub.inflate();
        } else {
            // 已经加载了
        }

    }

}

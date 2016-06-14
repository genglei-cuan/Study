package cn.steve.flyme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import cn.steve.study.R;
import steve.cn.mylib.flymelib.ActionBarProxy;

/**
 * 适配魅族的smartBar，不知道为何按照官方文档不行
 *
 *
 * Created by yantinggeng on 2015/11/2.
 */
public class FlymeAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        System.out.println("onCreateOptionsMenu");
        if (ActionBarProxy.hasSmartBar()) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.flyme_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");
        super.onResume();
    }
}

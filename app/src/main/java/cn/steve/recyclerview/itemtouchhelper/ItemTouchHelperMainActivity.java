package cn.steve.recyclerview.itemtouchhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/2.
 */
public class ItemTouchHelperMainActivity extends AppCompatActivity
    implements MainFragment.OnListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_itemtouch_main);

        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
        }

    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment=new RecyclerListFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment)
            .addToBackStack(null)
            .commit();
    }
}

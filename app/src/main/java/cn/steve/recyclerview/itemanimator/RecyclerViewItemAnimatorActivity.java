package cn.steve.recyclerview.itemanimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import cn.steve.study.R;

/**
 * 来源于  https://github.com/gabrielemariotti/RecyclerViewItemAnimators
 *
 * Created by yantinggeng on 2015/10/27.
 */
public class RecyclerViewItemAnimatorActivity extends AppCompatActivity {

    public static final String[] sCheeseStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats"};
    RecyclerView mRecyclerView;
    RecyclerViewItemAnimatorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            mAdapter.add("New String", RecyclerViewItemAnimatorAdapter.LAST_POSITION);
            return true;
        }
        if (id == R.id.action_remove) {
            mAdapter.remove(RecyclerViewItemAnimatorAdapter.LAST_POSITION);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

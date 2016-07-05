package cn.steve.viewdraghelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.steve.study.R;
import steve.cn.mylib.view.YoutubeLayout;

/**
 * Created by yantinggeng on 2016/7/5.
 */
public class ViewDragHelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        final TextView viewHeader = (TextView) findViewById(R.id.header);
        final YoutubeLayout youtubeLayout = (YoutubeLayout) findViewById(R.id.dragLayout);
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewHeader.setText(listView.getAdapter().getItem(i).toString());
                youtubeLayout.setVisibility(View.VISIBLE);
                youtubeLayout.maximize();
            }
        });

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public String getItem(int i) {
                return "object" + i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View rView, ViewGroup viewGroup) {
                View view = rView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                }
                ((TextView) view.findViewById(android.R.id.text1)).setText(getItem(i));
                return view;
            }
        });
    }
}

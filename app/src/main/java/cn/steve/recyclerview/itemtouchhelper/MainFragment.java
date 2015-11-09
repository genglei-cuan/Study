package cn.steve.recyclerview.itemtouchhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by yantinggeng on 2015/11/9.
 */
public class MainFragment extends ListFragment {

    private OnListItemClickListener mItemClickListener;

    public MainFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mItemClickListener = (OnListItemClickListener) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String[] items = {"List", "Grid"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                                                android.R.layout.simple_list_item_1,
                                                                items);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mItemClickListener.onListItemClick(position);
    }

    public interface OnListItemClickListener {

        void onListItemClick(int position);
    }
}

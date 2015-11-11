package cn.steve.materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MaterialItemListFragment extends ListFragment {

    private static final String ARG_PARAM1 = "DATAS";
    private String[] datas = null;
    private Interactior mInteractior;

    public MaterialItemListFragment() {
    }

    public static MaterialItemListFragment newInstance(String[] data) {
        MaterialItemListFragment fragment = new MaterialItemListFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(ARG_PARAM1, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            datas = getArguments().getStringArray(ARG_PARAM1);
        }
        setListAdapter(
            new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, datas));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mInteractior = (Interactior) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mInteractior.onSelectedItem(position);
    }


    public interface Interactior {

        void onSelectedItem(int position);
    }


}

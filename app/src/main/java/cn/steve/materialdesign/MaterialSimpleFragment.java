package cn.steve.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/2/19.
 */
public class MaterialSimpleFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1="";
    private String mParam2="";


    public static MaterialSimpleFragment newInstance(String s1, String s2) {
        MaterialSimpleFragment fragment = new MaterialSimpleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, s1);
        args.putString(ARG_PARAM2, s2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_main_textview, container, false);
        TextView textView = (TextView) inflate.findViewById(R.id.textViewMain);
        textView.setText(mParam1 + mParam2);
        return inflate;
    }
}

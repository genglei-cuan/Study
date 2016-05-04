package cn.steve.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/4/11.
 */
public class BottomSheetShareFragment extends BottomSheetDialogFragment {


    private RecyclerView bottomSheetShareRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_share, container, false);
        this.bottomSheetShareRecyclerView = (RecyclerView) view.findViewById(R.id.bottomSheetShareRecyclerView);

        return view;
    }
}

package cn.steve.bottomsheet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import cn.steve.study.R;
import steve.cn.mylib.commonutil.ToastUtil;

/**
 * Created by yantinggeng on 2016/4/11.
 */
public class BottomSheetShareFragment extends BottomSheetDialogFragment {

    private ArrayList<ShareItem> shareItems;
    private ShareGridAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.shareItems = MockUtil.createItems();
        this.adapter = new ShareGridAdapter(this.shareItems);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_share, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottomSheetShareRecyclerView);
        recyclerView.setHasFixedSize(true);
        int spacing = getResources().getDimensionPixelSize(R.dimen.space_24);
        int spanCount = 4;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new ShareGridAdapter.OnItemCLickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                ShareItem shareItem = shareItems.get(itemPosition);
                ToastUtil.show(view.getContext(), shareItem.getTitle(), Toast.LENGTH_SHORT);
            }
        });

        view.findViewById(R.id.shareButtonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        return bottomSheetDialog;
    }
}

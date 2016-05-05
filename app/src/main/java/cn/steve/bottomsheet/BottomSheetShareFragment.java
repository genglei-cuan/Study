package cn.steve.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/4/11.
 */
public class BottomSheetShareFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_share, container, false);
        RecyclerView bottomSheetShareRecyclerView = (RecyclerView) view.findViewById(R.id.bottomSheetShareRecyclerView);
        ShareGridAdapter adapter = new ShareGridAdapter(createItems(), new ShareGridAdapter.ItemListener() {
            @Override
            public void onItemClick(ShareItem item) {
                Toast.makeText(BottomSheetShareFragment.this.getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheetShareRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        bottomSheetShareRecyclerView.setLayoutManager(gridLayoutManager);
        bottomSheetShareRecyclerView.setAdapter(adapter);
        return view;
    }


    public ArrayList<ShareItem> createItems() {
        ArrayList<ShareItem> items = new ArrayList<>();
        items.add(new ShareItem(R.drawable.ic_preview_24dp, "Preview"));
        items.add(new ShareItem(R.drawable.ic_share_24dp, "Share"));
        items.add(new ShareItem(R.drawable.ic_link_24dp, "Get link"));
        items.add(new ShareItem(R.drawable.ic_content_copy_24dp, "Copy"));
        items.add(new ShareItem(R.drawable.ic_preview_24dp, "Preview"));
        items.add(new ShareItem(R.drawable.ic_share_24dp, "Share"));
        items.add(new ShareItem(R.drawable.ic_content_copy_24dp, "Copy"));
        items.add(new ShareItem(R.drawable.ic_preview_24dp, "Preview"));
        items.add(new ShareItem(R.drawable.ic_link_24dp, "Get link"));
        items.add(new ShareItem(R.drawable.ic_share_24dp, "Share"));
        items.add(new ShareItem(R.drawable.ic_link_24dp, "Get link"));
        items.add(new ShareItem(R.drawable.ic_content_copy_24dp, "Copy"));
        items.add(new ShareItem(R.drawable.ic_preview_24dp, "Preview"));
        items.add(new ShareItem(R.drawable.ic_share_24dp, "Share"));
        items.add(new ShareItem(R.drawable.ic_link_24dp, "Get link"));
        items.add(new ShareItem(R.drawable.ic_content_copy_24dp, "Copy"));
        return items;
    }


}

package cn.steve.bottomsheet;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/5.
 */
public class ShareGridAdapter extends RecyclerView.Adapter<ShareGridAdapter.ViewHolder> {

    private ArrayList<ShareItem> mItems;
    private ItemListener mListener;

    public ShareGridAdapter(ArrayList<ShareItem> items, ItemListener listener) {
        mItems = items;
        mListener = listener;
    }

    public void setListener(ItemListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheet_gridadapter_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface ItemListener {

        void onItemClick(ShareItem item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public AppCompatImageView imageView;
        public TextView textView;
        public ShareItem item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.bottomSheetImageView);
            textView = (TextView) itemView.findViewById(R.id.bottomSheetTextView);
        }

        public void setData(ShareItem item) {
            this.item = item;
            imageView.setImageResource(item.getDrawableRes());
            textView.setText(item.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

}

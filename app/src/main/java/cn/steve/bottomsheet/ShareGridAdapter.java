
package cn.steve.bottomsheet;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/5.
 */
public class ShareGridAdapter extends RecyclerView.Adapter<ShareGridAdapter.ShareViewHolder> implements View.OnClickListener {


    private ArrayList<ShareItem> mItems;
    private OnItemCLickListener listener;

    public ShareGridAdapter(ArrayList<ShareItem> mItems) {
        this.mItems = mItems;
    }

    @Override
    public ShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheet_gridadapter_item, parent, false);
        itemView.setOnClickListener(this);
        return new ShareViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShareViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View v) {
        this.listener.onClick(v);
    }

    public void setListener(OnItemCLickListener listener) {
        this.listener = listener;
    }

    public interface OnItemCLickListener {

        void onClick(View view);
    }

    class ShareViewHolder extends RecyclerView.ViewHolder {

        public AppCompatImageView imageView;
        public AppCompatTextView textView;
        public ShareItem item;

        public ShareViewHolder(View itemView) {
            super(itemView);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.bottomSheetImageView);
            textView = (AppCompatTextView) itemView.findViewById(R.id.bottomSheetTextView);
        }

        public void setData(ShareItem item) {
            this.item = item;
            imageView.setImageResource(item.getDrawableRes());
            textView.setText(item.getTitle());
        }

    }

}

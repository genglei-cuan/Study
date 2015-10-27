package cn.steve.recyclerview.multipleitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/27.
 */
public class MultipleItemRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private String[] datas;

    public MultipleItemRecycleAdapter(Context mContext, String[] datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    //根据不同的类型返回不同的holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.recycleview_multipleitem, null));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
            return new TextViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, null));
        } else {
            return null;
        }
    }

    //当绑定上holder的时候，根据不同的holder类型，操作不同的view
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).text.setText(datas[position]);
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).textViewRecylerMultiple.setText(datas[position]);
        }
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()
                                 : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE, ITEM_TYPE_TEXT
    }


    public static class TextViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public TextViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }


    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView textViewRecylerMultiple;

        public ImageViewHolder(View itemView) {
            super(itemView);
            textViewRecylerMultiple =
                (TextView) itemView.findViewById(R.id.textViewRecylerMultiple);
        }
    }


}

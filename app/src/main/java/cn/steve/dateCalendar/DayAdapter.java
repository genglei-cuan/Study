package cn.steve.dateCalendar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AdapterItem> datas;

    public void setDatas(ArrayList<AdapterItem> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == AdapterItem.TYPE_GROUP) {
            TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.daypicker_adapter_item_group, parent, false);
            viewHolder = new GroupViewHolder(view);
        }

        if (viewType == AdapterItem.TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daypicker_adapter_item, parent, false);
            viewHolder = new ItemViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterItem dayItem = datas.get(position);

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.dayNum.setText(dayItem.getDay());
            itemViewHolder.dayPrice.setText(dayItem.getPrice());
            itemViewHolder.dayMore.setText(dayItem.getStock()+"");
        }
        if (holder instanceof GroupViewHolder) {
            GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
            groupViewHolder.groupName.setText(dayItem.getGroup());
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getAdapterItemType();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public static TextView dayNum;
        public static TextView dayPrice;
        public static TextView dayMore;

        public ItemViewHolder(View itemView) {
            super(itemView);
            dayNum = (TextView) itemView.findViewById(R.id.dayNum);
            dayPrice = (TextView) itemView.findViewById(R.id.dayPrice);
            dayMore = (TextView) itemView.findViewById(R.id.dayMore);
        }
    }


    public static class GroupViewHolder extends RecyclerView.ViewHolder {

        public static TextView groupName;

        public GroupViewHolder(TextView itemView) {
            super(itemView);
            groupName = itemView;
        }
    }

}

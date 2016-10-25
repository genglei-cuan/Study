package cn.steve.dateCalendar;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;


/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayAdapter extends BaseDayAdapter {

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
        AdapterItem adapterItem = datas.get(position);

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            String day = TextUtils.isEmpty(adapterItem.getDay()) ? " " : adapterItem.getDay();
            String price = TextUtils.isEmpty(adapterItem.getPrice()) ? " " : adapterItem.getPrice();
            String stock = adapterItem.getStock() == 0 ? " " : "余" + adapterItem.getStock();

            itemViewHolder.dayNum.setText(day);
            itemViewHolder.dayPrice.setText(price);
            itemViewHolder.dayMore.setText(stock);
        }

        if (holder instanceof GroupViewHolder) {
            GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
            groupViewHolder.groupName.setText(adapterItem.getGroup());
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

        public TextView dayNum;
        public TextView dayPrice;
        public TextView dayMore;

        public ItemViewHolder(View itemView) {
            super(itemView);
            dayNum = (TextView) itemView.findViewById(R.id.dayNum);
            dayPrice = (TextView) itemView.findViewById(R.id.dayPrice);
            dayMore = (TextView) itemView.findViewById(R.id.dayMore);
        }
    }


    public static class GroupViewHolder extends RecyclerView.ViewHolder {

        public TextView groupName;

        public GroupViewHolder(TextView itemView) {
            super(itemView);
            groupName = itemView;
        }
    }

}

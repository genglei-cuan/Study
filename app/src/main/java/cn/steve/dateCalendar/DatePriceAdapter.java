package cn.steve.dateCalendar;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.dateCalendar.basic.BaseDatePriceAdapter;
import cn.steve.study.R;


/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DatePriceAdapter extends BaseDatePriceAdapter<AdapterItem> {

    private ArrayList<AdapterItem> datas;
    private AdapterItem currentSelectedItem = null;

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
            view.setOnClickListener(this);
            viewHolder = new ItemViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterItem adapterItem = getItem(position);

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            String day = TextUtils.isEmpty(adapterItem.getDay()) ? " " : adapterItem.getDay();
            String price = TextUtils.isEmpty(adapterItem.getPrice()) ? " " : adapterItem.getPrice();
            String stock = adapterItem.getStock() == 0 ? " " : "余" + adapterItem.getStock();

            itemViewHolder.dayNum.setText(day);
            itemViewHolder.dayPrice.setText(price);
            itemViewHolder.dayMore.setText(stock);

            Resources resources = itemViewHolder.dayNum.getContext().getApplicationContext().getResources();
            int dayColor = resources.getColor(R.color.black);
            int priceColor = resources.getColor(R.color.price_color);
            int moreColor = resources.getColor(R.color.more_color);
            int itemBackground = resources.getColor(R.color.white);

            if (!adapterItem.isEffective()) {
                dayColor = resources.getColor(R.color.gray);
            }

            if (adapterItem.isSelected()) {
                itemBackground = resources.getColor(R.color.selected_color);
                moreColor = priceColor = dayColor = resources.getColor(R.color.white);
            }

            itemViewHolder.itemView.setBackgroundColor(itemBackground);
            itemViewHolder.dayNum.setTextColor(dayColor);
            itemViewHolder.dayPrice.setTextColor(priceColor);
            itemViewHolder.dayMore.setTextColor(moreColor);
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
        return getItem(position).getAdapterItemType();
    }

    public AdapterItem getItem(int position) {
        return datas.get(position);
    }

    @Override
    public void onClick(View v) {
        if (this.recyclerView == null) {
            return;
        }

        int position = this.recyclerView.getChildAdapterPosition(v);

        AdapterItem item = getItem(position);
        if (!item.isEffective()) {
            return;
        }
        if (item.isSelected()) {
            return;
        }
        item.setSelected(true);
        notifyItemChanged(position);
        currentSelectedItem = item;

        for (int i = 0; i < datas.size(); i++) {
            if (i == position) {
                continue;
            }
            AdapterItem adapterItem = datas.get(i);
            if (adapterItem.getAdapterItemType() == AdapterItem.TYPE_GROUP) {
                continue;
            }
            if (adapterItem.isSelected()) {
                adapterItem.setSelected(false);
                notifyItemChanged(i);
            } else {
                adapterItem.setSelected(false);
            }
        }
        if (this.onItemClickListener == null) {
            return;
        }
        this.onItemClickListener.onItemClick(this.recyclerView, v, position, position);
    }

    @Override
    protected AdapterItem getSelected() {
        return currentSelectedItem;
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

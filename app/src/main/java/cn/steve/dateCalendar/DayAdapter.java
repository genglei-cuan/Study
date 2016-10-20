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

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    private ArrayList<DayItem> dayItems;

    public void setDayItems(ArrayList<DayItem> dayItems) {
        this.dayItems = dayItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daypicker_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DayItem dayItem = dayItems.get(position);

        int type = dayItem.getType();
        int topColor = R.color.black;
        if (type == DayItem.TYPE_HOILDAY) {
            topColor = R.color.blue_light;
        }
        holder.dayNum.setTextColor(topColor);


        holder.dayNum.setText(dayItem.getDay());
        holder.dayPrice.setText(dayItem.getPrice());
        holder.dayMore.setText(dayItem.getMore());
    }

    @Override
    public int getItemCount() {
        return dayItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public static TextView dayNum;
        public static TextView dayPrice;
        public static TextView dayMore;

        public ViewHolder(View itemView) {
            super(itemView);
            dayNum = (TextView) itemView.findViewById(R.id.dayNum);
            dayPrice = (TextView) itemView.findViewById(R.id.dayPrice);
            dayMore = (TextView) itemView.findViewById(R.id.dayMore);
        }
    }

}

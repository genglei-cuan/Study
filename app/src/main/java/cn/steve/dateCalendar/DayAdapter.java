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

    private ArrayList<AdapterItem> datas;

    public void setDatas(ArrayList<AdapterItem> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daypicker_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AdapterItem dayItem = datas.get(position);



        holder.dayNum.setText(dayItem.getDate());
        holder.dayPrice.setText(dayItem.getPrice());
        holder.dayMore.setText(dayItem.getStock());
    }

    @Override
    public int getItemCount() {
        return datas.size();
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

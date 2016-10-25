package cn.steve.dateCalendar;

import android.support.v7.widget.RecyclerView;

/**
 * Created by yantinggeng on 2016/10/25.
 */

public abstract class BaseDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public boolean isGroupTitle(int position) {
        return getItemViewType(position) == AdapterItem.TYPE_GROUP;
    }
}

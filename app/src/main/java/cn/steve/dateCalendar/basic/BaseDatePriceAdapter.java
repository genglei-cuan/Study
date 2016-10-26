package cn.steve.dateCalendar.basic;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.steve.dateCalendar.AdapterItem;

/**
 * Created by yantinggeng on 2016/10/25.
 */

public abstract class BaseDatePriceAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    protected RecyclerView recyclerView;
    protected OnAdapterItemClickListener onItemClickListener;

    protected abstract T getSelected();


    public void setOnItemClickListener(OnAdapterItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public boolean isGroupTitle(int position) {
        return getItemViewType(position) == AdapterItem.TYPE_GROUP;
    }

    public interface OnAdapterItemClickListener {

        void onItemClick(RecyclerView parent, View view, int position, long id);
    }


}

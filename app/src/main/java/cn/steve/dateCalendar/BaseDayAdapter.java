package cn.steve.dateCalendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yantinggeng on 2016/10/25.
 */

public abstract class BaseDayAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    protected RecyclerView recyclerView;
    protected OnAdapterItemClickListener onItemClickListener;

    abstract T getSelected();


    public void setOnItemClickListener(OnAdapterItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public boolean isGroupTitle(int position) {
        return getItemViewType(position) == AdapterItem.TYPE_GROUP;
    }

    interface OnAdapterItemClickListener {

        void onItemClick(RecyclerView parent, View view, int position, long id);
    }


}

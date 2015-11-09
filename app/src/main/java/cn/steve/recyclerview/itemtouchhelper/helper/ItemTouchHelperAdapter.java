package cn.steve.recyclerview.itemtouchhelper.helper;

/**
 * * Interface to listen for a move or dismissal event from a {@link
 * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 *
 * 用于在回调的过程中，在移动和删除之后，通知底层数据的更新的接口
 *
 * Created by yantinggeng on 2015/11/9.
 */

import android.support.v7.widget.RecyclerView;

public interface ItemTouchHelperAdapter {

    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and <strong>not</strong> at the end of a "drop" event.<br/> <br/>
     * Implementations should call {@link RecyclerView.Adapter#notifyItemMoved(int, int)} after
     * adjusting the underlying data to reflect this move.
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     * @return True if the item was moved to the new adapter position.
     * @see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
     * @see RecyclerView.ViewHolder#getAdapterPosition()
     */
    boolean onItemMove(int fromPosition, int toPosition);


    /**
     * Called when an item has been dismissed by a swipe.<br/> <br/> Implementations should call
     * {@link RecyclerView.Adapter#notifyItemRemoved(int)} after adjusting the underlying data to
     * reflect this removal.
     *
     * @param position The position of the item dismissed.
     * @see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
     * @see RecyclerView.ViewHolder#getAdapterPosition()
     */
    void onItemDismiss(int position);
}

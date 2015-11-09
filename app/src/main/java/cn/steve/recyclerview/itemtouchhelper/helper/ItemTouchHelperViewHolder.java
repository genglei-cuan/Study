package cn.steve.recyclerview.itemtouchhelper.helper;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 *
 * Interface to notify an item ViewHolder of relevant callbacks from {@link
 * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 *
 * Created by yantinggeng on 2015/11/9.
 */
public interface ItemTouchHelperViewHolder {
    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}

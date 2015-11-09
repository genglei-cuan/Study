package cn.steve.recyclerview.itemtouchhelper.helper;

import android.support.v7.widget.RecyclerView;

/**
 * 拖动的手动监听器
 *
 * Created by yantinggeng on 2015/11/9.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}

package cn.steve.listview.cache;

import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder {

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        /**
         * SparseArray是Android提供的一个工具类 ，用意是用来取代HashMap工具类的。
         * SparseArray是android里为<Interger,Object>这样的Hashmap而专门写的类,目的是提高效率。
         */
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

}

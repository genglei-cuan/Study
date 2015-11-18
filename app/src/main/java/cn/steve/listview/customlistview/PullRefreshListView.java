package cn.steve.listview.customlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by yantinggeng on 2015/11/18.
 */
public class PullRefreshListView extends ListView {


    public PullRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshListView(Context context) {
        super(context);
    }


}

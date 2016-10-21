package cn.steve.dateCalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayPickerView extends FrameLayout {

    private RecyclerView recyclerView;

    public DayPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.daypicker_layout, this, true);
        init();
        show();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.dayPickerRecyclerView);
    }

    private void show() {
    }

}

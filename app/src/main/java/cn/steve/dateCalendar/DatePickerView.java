package cn.steve.dateCalendar;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DatePickerView extends FrameLayout {

    private RecyclerView recyclerView;
    private BaseDatePriceAdapter adapter;

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.daypicker_layout, this, true);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.dayPickerRecyclerView);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                boolean isTitle = adapter.isGroupTitle(position);
                if (isTitle) {
                    return 7;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void setAdapter(BaseDatePriceAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setRecyclerView(this.recyclerView);
        this.recyclerView.setAdapter(adapter);
    }

}

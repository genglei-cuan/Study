package cn.steve.dateCalendar;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayPickerView extends FrameLayout {

    private RecyclerView recyclerView;
    private Date defalutSelectedDate;
    private Calendar today;
    private Date todayDate;
    private ArrayMap<Integer, Integer> showingYearAndMonths;

    public DayPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.daypicker_layout, this, true);
        init();
        show();
    }

    public ArrayMap<Integer, Integer> getShowingYearAndMonths() {
        return showingYearAndMonths;
    }

    public void setShowingYearAndMonths(ArrayMap<Integer, Integer> showingYearAndMonths) {
        this.showingYearAndMonths = showingYearAndMonths;
    }

    public Date getDefalutSelectedDate() {
        return defalutSelectedDate;
    }

    public void setDefalutSelectedDate(Date defalutSelectedDate) {
        this.defalutSelectedDate = defalutSelectedDate;
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.dayPickerRecyclerView);
        Locale locale = Locale.getDefault();
        today = Calendar.getInstance(locale);
        todayDate = today.getTime();
    }

    private void show() {
    }


    private ArrayList<DayItem> generate(int year, int month) {
        ArrayList<DayItem> dayItems = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


        return dayItems;
    }
}

package cn.steve.dateCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

/**
 * responsible to create dates
 *
 * Created by yantinggeng on 2016/10/21.
 */

public class DayPickerBuilder {

    private Calendar today;
    private Locale locale;

    public DayPickerBuilder() {
        init();
    }


    private void init() {
        locale = Locale.getDefault();
        today = Calendar.getInstance(locale);
    }

    public ArrayList<DayItem> generateMonth(int year, int month) {
        ArrayList<DayItem> dayItems = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        setMidnight(cal);

        dayItems.addAll(getStartEmptyList(cal));

        dayItems.add(getDayItem(cal));

        int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < actualMaximum; i++) {
            cal.add(Calendar.DATE, 1);
            dayItems.add(getDayItem(cal));
        }

        dayItems.addAll(getEndEmptyList(cal));

        return dayItems;
    }

    private ArrayList<DayItem> getStartEmptyList(Calendar cal) {
        ArrayList<DayItem> emptyList = new ArrayList<>();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        for (int i = 1; i < dayOfWeek; i++) {
            DayItem item = new DayItem();
            emptyList.add(item);
        }

        return emptyList;
    }

    private ArrayList<DayItem> getEndEmptyList(Calendar cal) {
        ArrayList<DayItem> emptyList = new ArrayList<>();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < 7 - dayOfWeek; i++) {
            DayItem item = new DayItem();
            emptyList.add(item);
        }

        return emptyList;
    }

    private DayItem getDayItem(Calendar cal) {
        DayItem item = new DayItem();

        boolean sameDate = sameDate(cal, today);

        String day;

        if (sameDate) {
            day = "今天";
        } else {
            day = cal.get(Calendar.DAY_OF_MONTH) + "";
        }

        item.setDay(day);
        item.setDate(formatDate(cal));

        boolean preDate = preDate(cal, today);
        if (preDate) {
            item.setType(DayItem.TYPE_PRE);
        } else {
            item.setType(DayItem.TYPE_NORMAL);
        }

        return item;
    }


    private void setMidnight(Calendar cal) {
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
    }

    private boolean sameDate(Calendar cal, Calendar ano) {
        return cal.compareTo(ano) == 0;
    }

    private boolean preDate(Calendar cal, Calendar ano) {
        return cal.compareTo(ano) < 0;
    }

    private boolean futureDate(Calendar cal, Calendar ano) {
        return cal.compareTo(ano) > 0;
    }

    private String formatDate(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date date = cal.getTime();
        return sdf.format(date);
    }
}

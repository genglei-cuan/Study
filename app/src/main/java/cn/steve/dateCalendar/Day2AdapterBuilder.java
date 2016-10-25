package cn.steve.dateCalendar;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by yantinggeng on 2016/10/24.
 */

public class Day2AdapterBuilder {

    private ArrayMap<String, String> vacations;
    private ArrayMap<String, DatePriceVO> datas;

    public Day2AdapterBuilder() {
        init();
    }

    private void init() {
        vacations = new ArrayMap<>();
    }

    public ArrayMap<String, DatePriceVO> getDatas() {
        return datas;
    }

    public void setDatas(ArrayMap<String, DatePriceVO> datas) {
        this.datas = datas;
    }

    public ArrayList<AdapterItem> generateAdapterDatas() {
        ArrayList<AdapterItem> adapterItems = new ArrayList<>();

        // 1. get the all year and months
        ArrayList<DateYearMonth> dateYearMonthArrayList = new ArrayList<>();
        Set<String> datesHasDatas = datas.keySet();
        for (String datesHasData : datesHasDatas) {
            DateYearMonth yearMonth = getYearMonth(datesHasData);
            if (yearMonth == null) {
                continue;
            }
            if (dateYearMonthArrayList.contains(yearMonth)) {
                continue;
            }
            dateYearMonthArrayList.add(yearMonth);
        }

        // 2. get all the DayItems
        DayPickerBuilder builder = new DayPickerBuilder();
        for (DateYearMonth dateYearMonth : dateYearMonthArrayList) {

            // group
            AdapterItem item = new AdapterItem();
            int year = dateYearMonth.getYear();
            int month = dateYearMonth.getMonth();
            item.setGroup(year + "年" + month + "月");
            item.setAdapterItemType(AdapterItem.TYPE_GROUP);
            adapterItems.add(item);

            // items
            ArrayList<DayItem> c = builder.generateMonth(year, month);
            adapterItems.addAll(convert(c));

        }

        return adapterItems;
    }


    private ArrayList<AdapterItem> convert(ArrayList<DayItem> dayItemArrayList) {
        ArrayList<AdapterItem> adapterItems = new ArrayList<>();

        for (DayItem dayItem : dayItemArrayList) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.setAdapterItemType(AdapterItem.TYPE_ITEM);

            String date = dayItem.getDate();
            adapterItem.setDate(date);
            adapterItem.setDay(dayItem.getDay());

            DatePriceVO datePriceVO = datas.get(date);
            if (datePriceVO == null) {
                adapterItems.add(adapterItem);
                continue;
            }
            String festival = vacations.get(date);

            adapterItem.setPrice(datePriceVO.getPrice());
            adapterItem.setFestival(festival);
            adapterItem.setStock(datePriceVO.getStock());

            adapterItems.add(adapterItem);
        }

        return adapterItems;
    }


    private DateYearMonth getYearMonth(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        DateYearMonth dateYearMonth = new DateYearMonth();
        String[] strings = date.split("-");
        if (strings.length < 2) {
            return null;
        }

        String year = strings[0];
        String month = strings[1];

        if (TextUtils.isEmpty(year) || TextUtils.isEmpty(month)) {
            return null;
        }
        dateYearMonth.setYear(Integer.valueOf(year));
        dateYearMonth.setMonth(Integer.valueOf(month));

        return dateYearMonth;
    }

    private static class DateYearMonth {

        private int year;
        private int month;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (object == this) {
                return true;
            }
            if (!(object instanceof DateYearMonth)) {
                return false;
            }
            DateYearMonth other = (DateYearMonth) object;
            return other.getYear() == this.getYear() && other.getMonth() == this.getMonth();
        }

        @Override
        public int hashCode() {
            return this.getYear() + this.getMonth();
        }
    }

}

package cn.steve.dateCalendar;

import android.text.TextUtils;

/**
 * Created by yantinggeng on 2016/10/25.
 */
public abstract class BaseBuilder {

    protected Day2AdapterBuilder.DateYearMonth getYearMonth(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        Day2AdapterBuilder.DateYearMonth dateYearMonth = new Day2AdapterBuilder.DateYearMonth();
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

    protected static class DateYearMonth {

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

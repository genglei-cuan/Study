
package cn.steve.Utils;

import java.util.Calendar;

public class CalendarUntil {

    static Calendar cal = Calendar.getInstance();

    public static int getCurrentDate() {
        int date = cal.get(Calendar.DATE);
        return date;
    }

    public static int getDateByOffset(int offset) {
        cal.add(Calendar.DATE, offset);
        int date = cal.get(Calendar.DATE);
        return date;
    }

    public static int getCurrentMonth() {
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int getMonthByOffset(int delta) {
        cal.add(Calendar.MONTH, delta);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int getCurrentYear() {
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static int getYearByOffset(int offset) {
        cal.add(Calendar.YEAR, offset);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static void setCalendar(int year, int month, int date) {
        cal.set(year, month, date);
    }

    public static void setYear(int year) {
        cal.set(Calendar.YEAR, year);
    }

    public static void setMonth(int month) {
        cal.set(Calendar.MONTH, month - 1);
    }

    public static void setDate(int date) {
        cal.set(Calendar.DATE, date);
    }

    public static int getCurrenMonthDays() {
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    public static int getMonthDaysByOffset(int offset) {
        getMonthByOffset(offset);
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;

    }

    public static int getLastDayOfMonth() {
        return cal.getMaximum(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {

        // System.out.println("Year:" + getCurrentYear());
        // System.out.println("Month:" + getCurrentMonth());
        // System.out.println("Date:" + getCurrentDate());
        //
        // System.out.println("PreMonth:" + getMonthByOffset(-1));
        System.out.println("pre:" + getYearByOffset(-1));

    }

}

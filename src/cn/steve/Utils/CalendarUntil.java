package cn.steve.Utils;

import java.util.Calendar;

public class CalendarUntil {

  static Calendar cal = Calendar.getInstance();


  public static int getCurrentDate() {
    int date = cal.get(Calendar.DATE);
    return date;
  }

  public static int getCurrentMonth() {
    int month = cal.get(Calendar.MONTH) + 1;
    return month;
  }


  public static int getCurrentYear() {
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
    cal.set(Calendar.MONTH, month-1);
  }

  public static void setDate(int date) {
    cal.set(Calendar.DATE, date);
  }
  

  public static int getCurrenMonthDays() {
    int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    return days;
  }

  public static void main(String[] args) {

    setMonth(12);
    System.out.println("Date:" + getCurrentDate());
    System.out.println("Year:" + getCurrentYear());
    System.out.println("Month:" + getCurrentMonth());



  }

}

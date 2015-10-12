package cn.steve.gallery;

/**
 * Created by Steve on 2015/10/12.
 */
public class MyGalleryModel {

    private int year;
    private int month;
    private int day;
    private int week;

    public MyGalleryModel(int year, int month, int day, int week) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
    }

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}

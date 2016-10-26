package cn.steve.dateCalendar.basic;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayItem {

    public static final int TYPE_PRE = 11;
    public static final int TYPE_NORMAL = 22;


    private String day;
    private String date;

    private
    @ItemType
    int type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public
    @ItemType
    int getType() {
        return type;
    }

    public void setType(@ItemType int type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @IntDef(flag = true, value = {TYPE_PRE, TYPE_NORMAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemType {

    }
}

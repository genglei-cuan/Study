package cn.steve.dateCalendar;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;


/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayItem {

    public static final int TYPE_PRE = 11;
    public static final int TYPE_NORMAL = 22;
    public static final int TYPE_HOILDAY = 33;


    private String day;
    private String price;
    private String more;
    private boolean isEffective = true;
    private Date date;
    private
    @ItemType
    int type;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEffective() {
        return isEffective;
    }

    public void setEffective(boolean effective) {
        isEffective = effective;
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

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @IntDef(flag = true, value = {TYPE_PRE, TYPE_NORMAL, TYPE_HOILDAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemType {

    }
}

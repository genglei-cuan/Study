package cn.steve.dateCalendar;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yantinggeng on 2016/10/21.
 */

public class AdapterItem {

    public static final int TYPE_GROUP = 11;
    public static final int TYPE_ITEM = 22;

    private String day;
    private String price;
    private int stock = 0;
    private String festival;
    private String date;
    private String group;

    private boolean isEffective;
    private boolean isSelected;

    private
    @AdapterItemType
    int adapterItemType;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public
    @AdapterItemType
    int getAdapterItemType() {
        return adapterItemType;
    }

    public void setAdapterItemType(@AdapterItemType int adapterItemType) {
        this.adapterItemType = adapterItemType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEffective() {
        return isEffective;
    }

    public void setEffective(boolean effective) {
        isEffective = effective;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @IntDef(flag = true, value = {TYPE_GROUP, TYPE_ITEM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AdapterItemType {

    }
}

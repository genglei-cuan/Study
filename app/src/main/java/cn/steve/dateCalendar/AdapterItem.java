package cn.steve.dateCalendar;

/**
 * Created by yantinggeng on 2016/10/21.
 */

public class AdapterItem {

    private String date;
    private String price;
    private int stock;
    private boolean isEffective;
    private boolean isSelected;

    private DayItem dayItem;

    public DayItem getDayItem() {
        return dayItem;
    }

    public void setDayItem(DayItem dayItem) {
        this.dayItem = dayItem;
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
}

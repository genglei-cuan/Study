package cn.steve.dateCalendar;

/**
 * Created by yantinggeng on 2016/10/24.
 */

public class DatePriceVO {

    private String date;
    private String price;
    private int stock;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

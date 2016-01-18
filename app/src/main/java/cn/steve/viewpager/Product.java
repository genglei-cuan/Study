package cn.steve.viewpager;

/**
 * Created by yantinggeng on 2016/1/18.
 */
public class Product {

    private String image;
    private String name;
    private String price;
    private String prePrice;

    public Product(String image, String name, String prePrice, String price) {
        this.image = image;
        this.name = name;

        this.prePrice = prePrice;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(String prePrice) {
        this.prePrice = prePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

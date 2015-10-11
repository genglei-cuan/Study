package cn.steve.eventbus.dummy;

/**
 * Created by yantinggeng on 2015/9/28.
 */
public class Item {

    public String id;
    public String content;

    public Item(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
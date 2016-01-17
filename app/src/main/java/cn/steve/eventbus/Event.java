package cn.steve.eventbus;

import java.util.List;

import cn.steve.eventbus.dummy.Item;

/**
 * Created by yantinggeng on 2015/9/28.
 */
public class Event {

    public static class ItemListEvent {

        private List<Item> items;

        public ItemListEvent(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }


    }


}

package cn.steve.eventbus.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentHelper {

    public static List<Item> Items = new ArrayList<Item>();

    public static Map<String, Item> Item_Map = new HashMap<String, Item>();

    static {
        addItem(new Item("1", "Item 1"));
        addItem(new Item("2", "Item 2"));
        addItem(new Item("3", "Item 3"));
        addItem(new Item("4", "Item 4"));
        addItem(new Item("5", "Item 5"));
        addItem(new Item("6", "Item 6"));
    }

    private static void addItem(Item item) {
        Items.add(item);
        Item_Map.put(item.id, item);
    }


}

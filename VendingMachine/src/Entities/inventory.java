package Entities;

import java.util.Map;

public class inventory {
    Map<String, item> items;

    public inventory() {
        items = new java.util.HashMap<>();
    }

    public void addItem(item item) {
        items.put(item.getCode(), item);
    }

    public item getItem(String code) {
        return items.get(code);
    }

    public void removeItem(String code) {
        items.remove(code);
    }

    public int isAvailable(String code) {
        return items.get(code).getQuantity()>0?1:0;
    }
}
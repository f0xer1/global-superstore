package com.ltp.globalsuperstore.repository;

import com.ltp.globalsuperstore.Item;

import java.util.ArrayList;
import java.util.List;

public class StoreReposytory {
    List<Item> items = new ArrayList<>();

    public Item getItem(int index){
        return items.get(index);
    }
    public List<Item> getItems() {
        return items;
    }
    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(int index, Item item) {
        items.set(index, item);
    }
}

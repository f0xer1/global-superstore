package com.ltp.globalsuperstore.service;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repository.StoreReposytory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StoreService {
    StoreReposytory reposytory = new StoreReposytory();


    public Item getItem(int index){
        return reposytory.getItem(index);
    }
    public List<Item> getItems() {
        return reposytory.getItems();
    }
    public void addItem(Item item) {
        reposytory.addItem(item);
    }

    public void updateItem(int index, Item item) {
        reposytory.updateItem(index, item);
    }


    public int getIndexFromId(String id) {
        for (int i = 0; i < reposytory.getItems().size(); i++) {
            if (reposytory.getItem(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }
    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }
    public Item getItemById(String id) {
        int index = getIndexFromId(id);
        return index == Constants.NOT_FOUND ? new Item() : getItem(index);
    }

    public String handleSubmit(Item item){
        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            addItem(item);
        } else if (within5Days(item.getDate(),getItem(index).getDate())) {
            updateItem(index, item);
        } else {
            status = Constants.FAILED_STATUS;
        }
        return status;
    }

}

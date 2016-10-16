package com.company.domain;

/**
 * Created by user on 10.10.2016.
 */
public class Item {
    private ItemType type;
    private String title;

    public Item(String title, ItemType type){
        this.title = title; this.type = type;
    }

    public ItemType getType(){
        return this.type;
    }

    public String getTitle(){
        return this.title;
    }
}

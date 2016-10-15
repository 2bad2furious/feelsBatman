package com.company.domain;

import java.util.ArrayList;

/**
 * Created by user on 10.10.2016.
 */
public class Player {
private String name;
    private ArrayList<Item> items;
    private int inventSize = 10;

    public Player(String name){
        items = new ArrayList<Item>();
        this.name = name;
    }

    public boolean addToInventory(Item item){
        if(items.size()==inventSize-1)
            return false;
        //

            items.add(item);
            return true;

    }
}

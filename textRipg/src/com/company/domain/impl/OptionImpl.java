package com.company.domain.impl;

import com.company.domain.Item;
import com.company.domain.Location;
import com.company.domain.Option;

/**
 * Created by user on 10.10.2016.
 */

public class OptionImpl implements Option{
    private String text;
    private Location location;
    private Item item;

    public OptionImpl(String text, Location location){
        this.text = text;
        this.location = location;
    }

    public OptionImpl(String text, Item item){
        this.text = text;
        this.item = item;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    public Item getItem(){
        return this.item;
    }
}

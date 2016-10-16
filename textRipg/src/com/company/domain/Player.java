package com.company.domain;

import java.util.ArrayList;

/**
 * Created by user on 10.10.2016.
 */
public class Player {
private String name;
    private ArrayList<Item> items;
    private int inventSize = 10;
    private Item Armor;
    private Item MainWeapon;

    public Player(String name){
        items = new ArrayList<Item>();
        this.name = name;
    }

    public Item addToInventory(Item item){
        switch(item.getType()){
            case armor:{if(this.Armor != null) this.Armor = item; else{ Item item2 = this.Armor; this.Armor = item; return item2;}}
            case mainWeapon:{if(this.MainWeapon != null) this.MainWeapon = item; else{ Item item2 = this.MainWeapon; this.MainWeapon = item; return item2;}}
            case potion:{/*TODO potion effects*/ return null;}
            default:{}
        }
        if(items.size()==inventSize-1)
            return item;
        //

            items.add(item);
            return null;

    }

    public Item takeFromInventory(Item item){
        Item item2 = items.get(items.indexOf(item));
        items.remove(item);
        return item2;
    }

    public Item getArmor(){
        return this.Armor;
    }

    public Item getMainWeapon(){
        return this.MainWeapon;
    }


}

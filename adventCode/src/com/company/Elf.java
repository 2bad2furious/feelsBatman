package com.company;

import java.util.ArrayList;

/**
 * Created by user on 16.01.2017.
 */
public class Elf {
    private int count = 0;
    private boolean blocked;

    public Elf(int presents){
        count = presents;
    }

    public int steal(){
        block();
        int a = getCount();
        count = 0;
        return a;
    }
    public int getCount(){
        return count;
    }
    private void block(){
        blocked = true;
    }
    public int add(int x) {
        count += x;
        return count;
    }

    public final boolean isBlocked(){
        return blocked;
    }

    public static ArrayList<Elf> getList(int a){
        ArrayList<Elf> arr  = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            arr.add(i,new Elf(1));
        }
        return arr;
    }
}

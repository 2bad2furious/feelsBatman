package com.company;

/**
 * Created by user on 06.11.2016.
 */
public class Hits {
    private Coordinates c;
    private int hitValue;

    public Hits(Coordinates c,int hitValue){
        this.c = c;
        this.hitValue = hitValue;
    }

    public Coordinates getCoordinates(){
        return c;
    }

    public int getHitValue(){
        return hitValue;
    }
}

package com.company;

/**
 * Created by user on 31.10.2016.
 */
public class Shippart {
    private Ship parent;
    private Coordinates c;

    public Shippart(Ship s){
        this.parent = s;
    }
    public Shippart(Coordinates c){this.c = c;}

    public void setParent(Ship s){
        parent = s;
    }

    public Ship getParent(){
        return this.parent;
    }

    public void setCoordinates(Coordinates c){
        if(c == null)
            this.c = c;
    }

    public Coordinates getCoordinates(){
        return c;
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by user on 31.10.2016.
 */
public class Ship {
private ArrayList<Shippart> parts;
private String name;
private Coordinates coordinates;
    private int rotated = -1;

    public Ship(ArrayList<Shippart> parts,String name){
        this.parts = parts;
        this.name = name;
    }

    public ArrayList<Shippart> getParts(){
        return parts;
    }

    public Ship checkIsSunkOrJustDamaged(){
        boolean sunk = true;
        for(Shippart a : parts){
            if(!(a instanceof DamagedShippart)) sunk = false;
        }
        if(sunk) {
            SunkShip parent = new SunkShip(parts,name);
            for(Shippart a : parent.getParts()){
                a.setParent(parent);
            }
            return parent;
        }else return this;

    }

    public void addPart(Shippart s){
        parts.add(s);
    }

    public String getName(){
        return this.name;
    }

    public int getSize(){
        return this.parts.size();
    }

    public Ship fix(){
        int size = parts.size();
        Shippart s;
        for(int i = 0;i<size;i++){
             s = parts.get(i);
            s.setParent(this);
                    parts.set(i,s);
        }
        return new Ship(parts,name);

    }
    public void setCoordinates(Coordinates c){
        if(coordinates==null)
        this.coordinates = c;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public void setRotated(int i){
        if(rotated == -1)
            rotated = i;
    }

    public int getRotated(){
        return rotated;
    }
}

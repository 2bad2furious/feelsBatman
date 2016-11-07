package com.company;

import java.util.ArrayList;

/**
 * Created by user on 06.11.2016.
 */
public class EnemyBattleField{
    private Shippart[][] field = new Shippart[10][10];
    private ArrayList<Ship> ships = new ArrayList<Ship>();

    public EnemyBattleField(ArrayList<Ship> ships){
        this.ships = ships;
    }

    public void insertPart(Shippart shippart){
        field[shippart.getCoordinates().getX()][shippart.getCoordinates().getY()] = new DamagedShippart(null);
    }

    public int checkCoordinates(Coordinates c){
        if(c.getX()>=field.length || c.getY()>=field.length) return -5;
        if(field[c.getX()][c.getX()].getParent() != null) return -1;
        else if(field[c.getX()][c.getX()] instanceof DamagedShippart) return 1;
        else return 0;
    }



}

package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by user on 27.10.2016.
 */
public class BattleField {
    private Shippart[][] field = new Shippart[10][10];
    private ArrayList<Ship> ships = new ArrayList<Ship>();

    public BattleField(ArrayList<Ship> availableShips,boolean bot) {
        int index;
        Ship curShip = null;
        Boolean valid;
        if (bot) {
            while (true) {
                if (availableShips.size() == 0)
                    break;

                curShip = availableShips.get(0);
                curShip.setRotated(Game.rn.nextInt(2) % 2);

                Coordinates c = new Coordinates(Game.rn.nextInt(10)%10,Game.rn.nextInt(10)%10);
                valid = checkValidShipPlacement(curShip, c);

                if(valid) {
                    curShip.setCoordinates(c);
                    placeShipOnField(c, curShip);
                    availableShips.remove(curShip);
                    ships.add(curShip);
                    curShip = null;
                }
            }
            } else{
                while (true) {
                    if (availableShips.size() == 0) break;

                    if (curShip == null) {
                        index = UserInteraction.listAvailableShipTypes(availableShips);
                        curShip = availableShips.get(index);
                    }

                    if (curShip.getSize() > 1)
                        index = UserInteraction.askForRotation();
                    else index = 0;

                    curShip.setRotated(index);

                    printField();
                    Coordinates c = UserInteraction.askForCoordinates();
                    valid = checkValidShipPlacement(curShip, c);

                    if (!valid) {
                        UserInteraction.reportWrongCoordinates();
                        continue;
                    }

                    curShip.setCoordinates(c);
                    placeShipOnField(c, curShip);
                    availableShips.remove(curShip);
                    ships.add(curShip);
                    curShip = null;
                }
            }
        }

    private void placeShipOnField(Coordinates c,Ship s){
        Shippart sp;
        if(s.getRotated() == 1){
            for(int i = 0; i < s.getSize();i++){
                s.getParts().get(i).setCoordinates(new Coordinates(c.getX(),c.getY()+i));
                sp = s.getParts().get(i);
                this.field[c.getX()][c.getY()+i] = s.getParts().get(i);
            }
        }else{
            for(int i = 0; i < s.getSize();i++){
                s.getParts().get(i).setCoordinates(new Coordinates(c.getX()+i,c.getY()));
                sp = s.getParts().get(i);
                this.field[c.getX()+i][c.getY()] = s.getParts().get(i);
            }
        }
    }

    public boolean checkValidShipPlacement(Ship ship,Coordinates c){
        try {
            if (c.getX() >= field.length || c.getY() >= field.length || c.getX() < 0 || c.getY() < 0)
                return false;

            if (ship.getRotated() == 0) {
                //check sticking ship out of the field
                if (c.getX() + ship.getSize() >= field.length)
                    return false;

                //check back collision
                if ((c.getX() - 1) >= 0) {
                    if (field[c.getX() - 1][c.getY()] != null)
                        return false;
                }

                //check front collision
                if ((c.getX() + ship.getSize() < field.length)) {
                    if (field[c.getX() + ship.getSize()][c.getY()] != null)
                        return false;
                }

                //check right side and left side
                for (int i = 0; i < ship.getSize(); i++) {
                    if (c.getY()+1<field.length){
                        if (field[c.getX() + i][c.getY() + 1] instanceof Shippart)
                            return false;
                }
                if(c.getY()-1>=0){
                if (field[c.getX() + i][c.getY() - 1] instanceof Shippart)
                    return false;
            }
        }

            } else {
                //check sticking ship out of the field
                if (c.getY() + ship.getSize() >= field.length)
                    return false;

                //check back collision
                if((c.getY() - 1) >=0) {
                    if (field[c.getX()][c.getY()-1] != null)
                        return false;
                }

                //check front collision
                if((c.getY()+ship.getSize() < field.length)){
                    if(field[c.getX()][c.getY()+ship.getSize()] != null)
                        return false;
                }

                //check right side and left side
                for (int i = 0; i < ship.getSize(); i++) {
                    if (c.getX()+1<field.length){
                        if (field[c.getX() + 1][c.getY() + i] instanceof Shippart)
                            return false;
                    }
                    if(c.getX()-1>=0){
                        if (field[c.getX() -1][c.getY() + i] instanceof Shippart)
                            return false;
                    }
                }
            }
        }catch(Exception ex){
            System.out.println("Exception");
            return false;
        }
        return true;
    }

    public int getCounter(){
        return this.ships.size();
    }

    public int hit(int x, int y){
        int i;
        if(this.field[x][y] instanceof DamagedShippart){
            i = -1;
        }else if(this.field[x][y] instanceof Shippart){
            if(field[x][y].getParent().checkIsSunkOrJustDamaged() instanceof SunkShip) i = -3;
            else i = 1;
            this.field[x][y] = new DamagedShippart(this.field[x][y].getParent());
        }else{
            i = 0;
        }
        return i;
    }

    public int getSizeX(){
        return this.field.length;
    }

    public int getSizeY(){
        return this.field.length;
    }

    public void printField(){
        UserInteraction.printUserField(this.field);
    }

    public void printEnemyField(){
        UserInteraction.printEnemyField(this.field);
    }
}

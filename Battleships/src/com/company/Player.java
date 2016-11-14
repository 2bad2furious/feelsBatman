package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by user on 27.10.2016.
 */
public class Player {
    private String name;
    private int score;
    private BattleField bf;
    private boolean bot = true;
    private EnemyBattleField enemyBf;
    private ArrayList<Hits> hits = new ArrayList<Hits>();

    public Player(String a,boolean bot){
        if(a == null){
            a = UserInteraction.askForName();
        }
        name = a;
        this.bot = bot;
        UserInteraction.reportSuccessOfPlayerAdding(a);
        bf = new BattleField(getDefaultTypes(),bot);
        enemyBf = new EnemyBattleField(getDefaultTypes());
    }


    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public BattleField getBf(){
        return this.bf;
    }

    public boolean getBot(){
        return this.bot;
    }

    private ArrayList<Ship> getDefaultTypes(){
        ArrayList<Ship> ships = new ArrayList<Ship>();
        ArrayList<Shippart> part = new ArrayList<Shippart>();
        part.add(new Shippart((Ship) null));
        ArrayList<Shippart> parts1 = new ArrayList<Shippart>();
        parts1.add(new Shippart((Ship) null));
        parts1.add(new Shippart((Ship) null));
        ArrayList<Shippart> parts2 = new ArrayList<Shippart>();
        parts2.add(new Shippart((Ship) null));
        parts2.add(new Shippart((Ship) null));
        parts2.add(new Shippart((Ship) null));
        ArrayList<Shippart> parts3 = new ArrayList<Shippart>();
        parts3.add(new Shippart((Ship) null));
        parts3.add(new Shippart((Ship) null));
        parts3.add(new Shippart((Ship) null));
        parts3.add(new Shippart((Ship) null));
        ArrayList<Shippart> parts4 = new ArrayList<Shippart>();
        parts4.add(new Shippart((Ship) null));
        parts4.add(new Shippart((Ship) null));
        parts4.add(new Shippart((Ship) null));
        parts4.add(new Shippart((Ship) null));
        parts4.add(new Shippart((Ship) null));

        ships.add(new Ship(part,"1x1").fix());
        ships.add(new Ship(parts1,"2x1").fix());
        ships.add(new Ship(parts2,"3x1").fix());
        ships.add(new Ship(parts3,"4x1").fix());
        ships.add(new Ship(parts4,"5x1").fix());

        return ships;
    }
    public int hit(int x, int y){
        int a = bf.hit(x,y);
        Coordinates c = new Coordinates(x,y);
        hits.add(new Hits(c,a));
        if(a==1){
            //hit
            enemyBf.insertPart(new DamagedShippart(c));
        }else if(a==-3){
            //SUNK
        }
        return a;
    }

    public EnemyBattleField getEnemyBf(){
        return this.enemyBf;
    }

    public void insert(Coordinates c, int HitValue){
        this.hits.add(new Hits(c,HitValue));
        this.enemyBf.insertPart(new DamagedShippart(c));
      }

    public ArrayList<Hits> getHits(){
        return hits;
    }
    }

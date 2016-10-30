package com.company;

/**
 * Created by user on 27.10.2016.
 */
public class Player {
    private String name;
    private int score;
    private BattleField bf;
    private boolean bot = true;

    public Player(String a,int[][] arr,boolean bot,int sizeX,int sizeY, int limit){
        name = a;
        this.bot = bot;
        UserInteraction.com("Player added, name: "+name);
        if(arr==null)
            UserInteraction.com("Now you need to place your ships");


        bf = new BattleField(arr,sizeX,sizeY,limit);
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
}

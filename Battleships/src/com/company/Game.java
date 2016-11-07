package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 27.10.2016.
 */
public class Game{
    private ArrayList<Player> players;
    public static Random rn = new Random();

    public Game(){
        players = new ArrayList<>();
    }

    public void createGame(){
        players.add(new Player("Bot1",true));
        players.add(new Player(null,false));
    }

    public ArrayList<Player> start(){
        int i = rn.nextInt(2);
        UserInteraction.reportStartOfBattle(players,i);
        return doRound(players.get(i),players.get((i+1)%2),0);
    }

    private ArrayList<Player> doRound(Player p1,Player p2,int i){
        if(p1.getBot()){
            if(p1.lastHit.getHitValue()==1){
                //checkEnemyField what he hit and stuff

            }
            int a = rn.nextInt(p2.getBf().getSizeX());
            int b = rn.nextInt(p2.getBf().getSizeY());
            int hit = p2.getBf().hit(a,b);

            p1.lastHit = new LastHit(new Coordinates(a,b),hit);
            UserInteraction.reportEnemyHit(hit,p2.getBf().getCounter());
        }else{
            Coordinates c = UserInteraction.InitializeStartOfHumanRound(p2.getBf());
            int a = c.getX();
            int b = c.getY();
            int hit = p2.getBf().hit(a,b);

            p1.lastHit = new LastHit(new Coordinates(a,b),hit);
            UserInteraction.reportUserHit(hit,p2.getBf().getCounter());
        }
        if(p2.getBf().getCounter()==0){
            ArrayList<Player> end = new ArrayList<>();
            end.add(p1);
            end.add(p2);
            return end;
        }
        return doRound(p2, p1,i+1);
    }
}

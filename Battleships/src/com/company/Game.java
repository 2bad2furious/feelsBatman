package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 27.10.2016.
 */
public class Game{
    private ArrayList<Player> players;
    private Random rn;

    public Game(){
        players = new ArrayList<>();
        rn = new Random();
    }

    public void createGame(){
        UserInteraction.com("Insert your name");
        String name1 = UserInteraction.askForWord();
        int[][] arr = new int[10][10];
        int limit = 20;
        for (int i = 0; i < limit; i++) {
            int x = rn.nextInt(11);
            int y = rn.nextInt(11);
            if(arr[(x)%10][(y)%10] == 1){
                i--;
            }else{
                arr[(x)%10][(y)%10] = 1;
            }

        }
        BattleField bf = new BattleField(arr,10,10,20);
        players.add(new Player("Bot1",arr,true,arr[0].length,arr.length,0));

        players.add(new Player(name1,null,false,10,10,20));

        }

    public ArrayList<Player> start(){
        UserInteraction.com("It's on!");
        UserInteraction.com(players.get(0).getName()+" starts with "+players.get(0).getBf().getCounter()+" ships");
        UserInteraction.com(players.get(1).getName()+" starts with "+players.get(1).getBf().getCounter()+" ships");
        int i = rn.nextInt(2);
        UserInteraction.com("Player "+players.get(i).getName()+" starts!");
        return doRound(players.get(i),
                players.get((i+1)%2),0);
    }

    private ArrayList<Player> doRound(Player p1,Player p2,int i){
        if(p1.getBot()){
            int a = rn.nextInt(p2.getBf().getSizeX());
            int b = rn.nextInt(p2.getBf().getSizeY());
            int hit = p2.getBf().hit(a,b);
            if(hit == 1){
                UserInteraction.com("You've been hit");
                UserInteraction.com("You still have "+p2.getBf().getCounter()+" left. :)");
                }else{
                    UserInteraction.com("The bot missed, phew");
            }
            int counter = p2.getBf().getCounter();
        }else{
            UserInteraction.com("It's your turn! :)");
            p2.getBf().printEnemyField();
            UserInteraction.com("Insert first index");
            int a = Integer.parseInt(UserInteraction.askForWord());
            UserInteraction.com("Insert second index");
            int b = Integer.parseInt(UserInteraction.askForWord());
            if(a>=p2.getBf().getSizeX()||b>= p2.getBf().getSizeY()){
                UserInteraction.com("Wrong index:( insert only numbers smaller than "+(p2.getBf().getSizeY()));
                return doRound(p1, p2, i);
            }
            int hit = p2.getBf().hit(a,b);

            if(hit == 1){
                UserInteraction.com("Target hit");
            }else if(hit == 0){
                UserInteraction.com("Splash :(");
            }else if(hit == -1){
                UserInteraction.com("You've already sunk that.");
            }
            int counter = p2.getBf().getCounter();
            if(counter>0)
                UserInteraction.com("You still have "+counter+" ships left to sink! :)");
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

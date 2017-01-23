package com.company;

import com.company.domain.Game;
import com.company.domain.GameImpl;
import com.company.domain.model.Player;

public class Main {

    public static void main(String[] args) {
        Game g = GameImpl.createNew(6,6);
        System.out.println(g.getBoard().fields()!= null);
        int x,y;
        int[] arr = null;
        play(g,arr);
        play(g,arr);
        while(true){
            play(g,arr);
            if(g.getCount(Player.FIRST_PLAYER)==0||g.getCount(Player.SECOND_PLAYER)==0){
                UI.printGG((g.getCount(Player.FIRST_PLAYER)==0)?Player.FIRST_PLAYER:Player.SECOND_PLAYER);
                System.exit(1);
            }
        }
    }

    private static void play(Game g,int[] arr){
        UI.print(g.getBoard().fields(),g.getCurrentPlayer(),g.getCount(Player.FIRST_PLAYER),g.getCount(Player.SECOND_PLAYER));
        arr = UI.getNextMove();
        g.onMoveMade(arr[0],arr[1]);
    }
}

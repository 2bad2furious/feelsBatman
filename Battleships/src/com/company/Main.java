package com.company;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Game g = new Game();
        g.createGame();
        ArrayList<Player> arr = g.start();

        UserInteraction.reportEnd();

        if(arr.get(0).getBot()){
            UserInteraction.reportUserLoss();
        }else{
            UserInteraction.reportUserWin();
        }
    }
}

package com.company;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Game g = new Game();
        g.createGame();
        ArrayList<Player> arr = g.start();
        UserInteraction.com("The end");

        if(arr.get(0).getBot()){
            UserInteraction.com("You've lost");
        }else{
            UserInteraction.com("You've Won!!!! Yay!!!!");
        }
    }
}

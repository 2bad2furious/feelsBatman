package com.company;

import com.company.domain.Game;
import com.company.domain.GameImpl;
import com.company.domain.model.AI;
import com.company.domain.model.Player;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.security.InvalidParameterException;

public class Main {

    public static void main(String[] args) {
        Game g = GameImpl.createNew(6, 6);
        playAgainstAI(g);
        playAgainstAI(g);
        while (true) {
            playAgainstAI(g);
            if (g.getCount(Player.FIRST_PLAYER) == 0 || g.getCount(Player.SECOND_PLAYER) == 0) {
                UI.printGG((g.getCount(Player.FIRST_PLAYER) == 0) ? Player.FIRST_PLAYER : Player.SECOND_PLAYER);
                break;
            }
        }
    }

    private static void play(Game g) {
        UI.print(g.getBoard().fields(), g.getCurrentPlayer(), g.getCount(Player.FIRST_PLAYER), g.getCount(Player.SECOND_PLAYER));
        makePlay(g, UI.getNextMove());
    }

    private static void playAgainstAI(Game g) {
        if (g.getCurrentPlayer() == Player.FIRST_PLAYER) play(g); //Player's move
        else makePlay(g, AI.getNextMove(g.getBoard().fields())); //AI's move
    }

    private static void makePlay(Game g, int[] arr) {
        if (arr.length != 2) throw new IllegalArgumentException();
        else g.onMoveMade(arr[0], arr[1]);
    }
}

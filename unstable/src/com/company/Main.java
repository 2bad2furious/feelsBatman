package com.company;

import com.company.domain.Game;
import com.company.domain.GameImpl;
import com.company.domain.model.AI.AI;
import com.company.domain.model.AI.AI1;
import com.company.domain.model.Coordinates;
import com.company.domain.model.Player;
import sun.plugin.dom.exception.InvalidStateException;
import java.util.Optional;

public class Main {
    private static Optional<AI> p1 = Optional.of(AI1.getInstance(Player.SECOND_PLAYER));
    private static Optional<AI> p2 = Optional.empty();

    public static void main(String[] args) {
        Game g = GameImpl.createNew(6, 6);
        play(g);
        play(g);
        while (g.getCount(Player.FIRST_PLAYER) != 0 && g.getCount(Player.SECOND_PLAYER) != 0)
            play(g);

        UI.printGG((g.getCount(Player.FIRST_PLAYER) == 0) ? Player.FIRST_PLAYER : Player.SECOND_PLAYER);
    }

    private static void play(Game g) {
        UI.print(g.getBoard().fields(), g.getCurrentPlayer(), g.getCount(Player.FIRST_PLAYER), g.getCount(Player.SECOND_PLAYER));

        Optional<AI> curPlayer = getCurrentAIOrUser(g);

        if(curPlayer.isPresent()) AIplay(g,curPlayer.get()); //AI play
        else Userplay(g); //user play

    }

    private static void Userplay(Game g){
        makePlay(g, UI.getNextMove());
    }

    private static void AIplay(Game g,AI ai) {
        makePlay(g, ai.getNextMove(g.getBoard().fields())); //AI's move
    }

    private static void makePlay(Game g, Coordinates c) {
        g.onMoveMade(c.getX(),c.getY());
    }

    private static Optional<AI> getCurrentAIOrUser(Game g){
        if(g.getCurrentPlayer() == Player.FIRST_PLAYER) return p1;
        else if(g.getCurrentPlayer() == Player.SECOND_PLAYER) return p2;
        else throw new InvalidStateException("FuCK");
    }
}

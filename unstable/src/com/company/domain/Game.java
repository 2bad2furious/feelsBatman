package com.company.domain;

import com.company.domain.model.GameBoard;
import com.company.domain.model.Player;

/**
 * Created by semanticer on 15.01.2017.
 */

public interface Game {
    GameBoard onMoveMade(int x, int y);
    boolean isMovePossible(int x, int y);
    GameBoard getBoard();
    Player getCurrentPlayer();
    int getCount(Player p);
}

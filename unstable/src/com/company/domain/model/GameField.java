package com.company.domain.model;

/**
 * Created by semanticer on 15.01.2017.
 */

public class GameField {
    private int atomCount;
    private Player player;
    public static final int MAXIMUM_STABLE_ATOM_COUNT = 3;

    public static GameField create(int atomCount, Player player) {

        if (atomCount > 0 && player == Player.ANON)
            throw new IllegalArgumentException("Field with some atoms must define their player owner");
        if (atomCount == 0 && player != Player.ANON)
            throw new IllegalArgumentException("Player cannot own zero atoms field");
        if (atomCount >= 0 && atomCount > MAXIMUM_STABLE_ATOM_COUNT)
            throw new IllegalArgumentException("Cannot set atomCount to " + atomCount + ". Must fit to range 0 - " + MAXIMUM_STABLE_ATOM_COUNT);

        return new GameField(atomCount, player);
    }

    private GameField(int atomCount, Player player){
        this.atomCount = atomCount;
        this.player = player;
    }

    public static GameField createBlank() {
        return new GameField(0, Player.ANON);
    }

    public int atomCount(){
        return atomCount;
    }
    public Player player(){
        return player;
    }
}

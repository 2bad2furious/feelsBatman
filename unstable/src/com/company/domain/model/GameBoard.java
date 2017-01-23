package com.company.domain.model;
import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */
public class GameBoard {
    private List<List<GameField>> field;
    private int rows;
    private int columns;

    public static GameBoard create(List<List<GameField>> fields) {
        if(fields == null || fields.size() == 0 || fields.get(0).size() == 0)
            throw new IllegalArgumentException("Illegal state fields");

        return new GameBoard(fields, fields.size(), fields.get(0).size());
    }

    private GameBoard(List<List<GameField>> fields,int rows, int columns){
        this.field = fields; this.rows = rows; this.columns = columns;
    }

    public List<List<GameField>> fields(){
        return this.field;
    }
    public int rows(){
        return this.rows;
    }
    public int columns(){
        return this.columns;
    }
}

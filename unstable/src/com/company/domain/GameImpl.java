package com.company.domain;

import com.company.domain.model.GameBoard;
import com.company.domain.model.GameField;
import com.company.domain.model.Player;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */

public class GameImpl implements Game {

    private GameBoard gameBoard;
    private Player playerOnTurn;

    public static GameImpl createNew(int rowCount, int columnCount) {
        return new GameImpl(rowCount, columnCount);
    }

    private GameImpl(int rowCount, int columnCount) {

        List<List<GameField>> fields = new ArrayList<>();

        // TODO fill up fields with empty GameFields - done

        for (int i = 0; i < rowCount; i++) {
            fields.add(i, new ArrayList<>());
            for (int j = 0; j < columnCount; j++) {
                fields.get(i).add(j, GameField.createBlank());
            }
        }

        gameBoard = GameBoard.create(fields); // TODO replace with GameBoard.create(fields); - done?
        playerOnTurn = Player.FIRST_PLAYER;
    }


    private void switchPlayerOnTurn() {
        playerOnTurn = playerOnTurn == Player.FIRST_PLAYER ? Player.SECOND_PLAYER : Player.FIRST_PLAYER;
    }

    @Override
    public GameBoard onMoveMade(int x, int y) {
        if (!isMovePossible(x, y)) {
            throw new IllegalStateException("Impossible to make move to position x: " + x + " y: " + y);
        }
        // TODO return new GameBoard after this move

        play(x,y);

        switchPlayerOnTurn();
        return GameBoard.create(gameBoard.fields());
        /*return  getSampleBoard(); // TODO replace with new and CORRECT GameBoard - done*/
    }
    private void play(int x,int y){
        List<List<GameField>> field = gameBoard.fields();
        if(field.get(x).get(y).atomCount() == 3){
            explode(x,y);
        }else{
            field.get(x).set(y,GameField.create(field.get(x).get(y).atomCount()+1,playerOnTurn));
        }
    }

    private void explode(int x, int y){
        gameBoard.fields().get(x).set(y,GameField.create(0,Player.ANON));
        if(isOnBoard(x+1,y)) play(x+1,y);
        if(isOnBoard(x-1,y)) play(x-1,y);
        if(isOnBoard(x,y+1)) play(x,y+1);
        if(isOnBoard(x,y-1)) play(x,y-1);
    }
    @Override
    public boolean isMovePossible(int x, int y) {
        GameField gf = gameBoard.fields().get(x).get(y);
        boolean onBoard = isOnBoard(x,y);
        boolean playerCheck = (gf.player() == playerOnTurn || gf.player() == Player.ANON);
        return (onBoard && playerCheck);
    }

    private boolean isOnBoard(int x, int y) {
        List<List<GameField>> field = gameBoard.fields();
        return (x >= 0 && x < field.size() && y >= 0 && y < field.get(x).size());
    }

    public int getCount(Player player){
        if(player != Player.FIRST_PLAYER && player != Player.SECOND_PLAYER && player != Player.ANON)
            throw new InvalidStateException("cannot get count of other players");
        int a = 0;
        for (List<GameField> arr:gameBoard.fields()) {
            for(GameField o:arr){
                a+= (o.player() == player)?o.atomCount():0;
            }
        }
        return a;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer(){
        return  playerOnTurn;
    }


}

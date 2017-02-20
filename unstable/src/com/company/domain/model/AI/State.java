package com.company.domain.model.AI;

import com.company.domain.model.Coordinates;
import com.company.domain.model.GameBoard;
import com.company.domain.model.GameField;
import com.company.domain.model.Player;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19.02.2017.
 */
public class State {

    public final Player player;
    private List<List<GameField>> field;
    private Coordinates coordinates;
    private int maxDepth;
    public final int depth;
    private List<State> availableMoves = new ArrayList<>();

    public State(Coordinates coordinates, List<List<GameField>> fields, Player player, int depth, int maxDepth) {
        this.field = new ArrayList<>(fields);
        this.player = player;
        this.depth = depth;
        this.maxDepth = maxDepth;
        if (coordinates != null) {
            this.coordinates = coordinates;
            simPlay(coordinates);
        }
        if (maxDepth > depth) availableMoves = getAvailableMoves(field);
    }

    private List<State> getAvailableMoves(List<List<GameField>> field) {
        List<State> f = new ArrayList<>();
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if(field.get(i).get(j).player() == player || field.get(i).get(j).player() == Player.ANON)
                f.add(new State(new Coordinates(i, j), new ArrayList<>(field), player, depth + 1, maxDepth));
            }
        }

        return f;
    }

    private int eval() {
        int myCount = 0;
        int otherCount = 0;
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                GameField g = field.get(i).get(j);
                if (g.player() == player) myCount++;
                else if (g.player() != Player.ANON) otherCount++;
            }
        }
        return myCount - otherCount;
    }

    private void simPlay(Coordinates c) {
        if (!isInField(c) && hasRightToPlay(c)) throw new InvalidParameterException();
        GameField gf = field.get(c.getX()).get(c.getY());

        if (gf.atomCount() < 3){
            field.get(c.getX()).set(c.getY(), GameField.create(gf.atomCount() + 1, player));
        }else{
            field.get(c.getX()).set(c.getY(),GameField.createBlank());
            explode(c);
        }
    }

    public void explode(Coordinates c) {
        Coordinates newC = new Coordinates(c.getX() + 1, c.getY());
        if (isInField(newC)) simPlay(newC);
        newC = new Coordinates(c.getX(), c.getY() + 1);
        if (isInField(newC)) simPlay(newC);
        newC = new Coordinates(c.getX() - 1, c.getY());
        if (isInField(newC)) simPlay(newC);
        newC = new Coordinates(c.getX(), c.getY() - 1);
        if (isInField(newC)) simPlay(newC);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public List<State> getAvailableMoves() {
        return availableMoves;
    }

    private boolean isInField(Coordinates c) {
        return (c.getX() >= 0 && c.getX() < field.size() && c.getY() >= 0 && c.getY() < field.get(c.getX()).size());
    }

    private boolean hasRightToPlay(Coordinates c) {
        return (field.get(c.getX()).get(c.getY()).player() == player || field.get(c.getX()).get(c.getY()).player() == Player.ANON);
    }
}

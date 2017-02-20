package com.company.domain.model.AI;

import com.company.domain.model.Coordinates;
import com.company.domain.model.GameField;
import com.company.domain.model.Player;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 12.02.2017.
 */
public class AI1 implements AI {
    private static HashMap<Player, AI1> instances = new HashMap<>();
    private Player player;
    private State latestState;

    private AI1(Player p) {
        this.player = p;
    }

    public static AI1 getInstance(Player p) {
        if (instances.containsKey(p))
            return instances.get(p);
        else {
            instances.put(p, new AI1(p));
            return instances.get(p);
        }
    }

    public Coordinates getNextMove(List<List<GameField>> field) {
        if(getMyCount(field) == 0) return getRandomMove(field);
        return max(new State(null, field, player, 0, 3)).getCoordinates();
    }

    private State max(State state){
        List<State> list = state.getAvailableMoves();

        if(list.size() == 0) return state;

        State max = list.get(0);
        int maxValue = list.get(0).value;
        for (State s:state.getAvailableMoves()){
            if(s.value > maxValue){
                maxValue = s.value;
                max = s;
            }
        }
        return max;
    }

    private State min(State state){
        List<State> list = state.getAvailableMoves();

        if(list.size() == 0) return state;


        State min = list.get(0);
        int minValue = list.get(0).value;

        for (State s:list){
            if(s.value < minValue){
                minValue = s.value;
                min = s;
            }
        }
        return min;
    }

    private int getMyCount(List<List<GameField>> field){
        int count = 0;
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if(field.get(i).get(j).player() == player) count++;
            }
        }
        System.out.println("Count:" +count);
        return count;
    }

    private Coordinates getRandomMove(List<List<GameField>> field){
        System.out.println("Random");
        Random rn = new Random();
        int x = rn.nextInt(field.size());
        return new Coordinates(x,rn.nextInt(field.get(x).size()));
    }

    private Coordinates oldGetNextMove(List<List<GameField>> field) {
        Coordinates c = new Coordinates(-1, -1);
        int maxCount = -1;
        GameField g;
        List<List<GameField>> arr = new ArrayList<>(field);

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                g = arr.get(i).get(j);
                if ((g.player() == player || g.player() == Player.ANON) && g.atomCount() > maxCount) {
                    maxCount = g.atomCount();
                    c = new Coordinates(i, j);
                }
            }
        }
        return c;
    }


}

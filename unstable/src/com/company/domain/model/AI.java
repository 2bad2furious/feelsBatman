package com.company.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 12.02.2017.
 */
public class AI {
    public static int[] getNextMove(List<List<GameField>> field){
        int[] retArr = {-1,-1};
        int maxCount = -1;
        GameField g;
        List<List<GameField>> arr = new ArrayList<>(field);

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                g = arr.get(i).get(j);
                if(g.player() != Player.FIRST_PLAYER && g.atomCount() > maxCount){
                    maxCount = g.atomCount();
                    retArr[0] = i;
                    retArr[1] = j;
                }
            }
        }
        
        return retArr;
    }
}

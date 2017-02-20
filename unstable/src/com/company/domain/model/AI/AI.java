package com.company.domain.model.AI;

import com.company.domain.model.Coordinates;
import com.company.domain.model.GameField;

import java.util.List;

/**
 * Created by user on 13.02.2017.
 */
public interface AI {
    public Coordinates getNextMove(List<List<GameField>> field);
}

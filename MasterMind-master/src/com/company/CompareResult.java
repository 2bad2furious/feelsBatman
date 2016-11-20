package com.company;

/**
 * Created by semanticer on 3. 12. 2015.
 */
public class CompareResult {
    public int correctColor;
    public int correctColorAndPlace;
    private int matchColors;
    private int matchPositions;
    private boolean isFinalAttempt;

    // TODO implement
    public CompareResult(int matchColors,int matchPositions,boolean isFinalAttempt){
        this.matchColors = matchColors;
        this.matchPositions = matchPositions;
        this.isFinalAttempt = isFinalAttempt;
    }

    public int getMatchColors(){
        return this.matchColors;
    }

    public int getMatchPositions(){
        return this.matchPositions;
    }

    public boolean isFinalAttempt(){
        return isFinalAttempt;
    }
}

package com.company;

/**
 * Created by user on 05.12.2016.
 */
public enum FieldValue {
    Bomb(-1),One(1),Two(2),Three(3),Four(4),Five(5),Six(6),Seven(7),Eight(8),Nine(9);
    private final int value;
    private boolean flagged;
    private boolean revealed;

    FieldValue(int v){
        value = v;
    }

    public boolean toggleFlagged(){
        flagged = (flagged)?false:true;
        return !(flagged);
    }

    public int getValue(){
        return this.value;
    }

    public boolean isFlagged(){
        return this.flagged;
    }

    public void reveal() {
        revealed = true;
    }

    public boolean isRevealed(){
        return this.revealed;
    }
}

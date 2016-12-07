package com.company;

/**
 * Created by user on 05.12.2016.
 */
public class FieldValue {
    public final int locx;
    public final int locy;
    private final int value;
    private Status status;

    public FieldValue(int x, int y, int val) {
        locx = x;
        locy = y;
        value = val;
        status = Status.DEFAULT;
    }

    public void reveal() {
        status = Status.REVEALED;
    }

    public void question() {
        status = Status.QUESTIONED;
    }

    public void flag() {
        status = Status.FLAGGED;
    }

    public int getVal() {
        return value;
    }

    public Status status() {
        return status;
    }
}


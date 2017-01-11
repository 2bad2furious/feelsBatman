package com.company;

import java.util.ArrayList;

/**
 * Created by user on 09.01.2017.
 */
public class ArrayObject {
    private char character;
    private int count;

    public ArrayObject(char character,int count){
        this.character = character;
        this.count = count;
    }

    public char getCharacter(){
        return character;
    }

    public int getCount(){
        return count;
    }

    public void increase(){
        count++;
    }

    public static ArrayObject[] getArray(){
            Character[] charArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            int[] intArr = new int[charArr.length];
            ArrayObject[] arr = new ArrayObject[charArr.length];
            for (int i = 0; i < charArr.length; i++) {
                arr[i] = new ArrayObject(charArr[i], 0);
            }
            return arr;
    }
}

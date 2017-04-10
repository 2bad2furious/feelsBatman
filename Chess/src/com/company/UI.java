package com.company;

import com.company.model.Coordinates;

import java.util.Scanner;

/**
 * Created by user on 01.03.2017.
 */
public class UI {
    private static Scanner sc = new Scanner(System.in);

    public static Coordinates getNextMove(){
        return new Coordinates(sc.nextInt(),sc.nextInt());
    }
}

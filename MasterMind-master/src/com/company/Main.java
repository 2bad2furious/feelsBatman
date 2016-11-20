package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int NUM_OF_PIECES = 4;
    public static final int NUM_OF_ATTEMPTS = 10;
    //pocet pokusu

    public static void main(String[] args) throws IOException {

        Color inputArr [] = new Color[NUM_OF_PIECES];
        int count = 0;


        List<Color> colorList = new ArrayList<>();

        colorList.add(Color.ORANGE);
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.ORANGE);



        Treasure myTreasure = new Treasure(colorList);
        CompareManager comparator = new CompareManager(myTreasure);

        System.out.println("Start");

        char inputChar = ' ';
        while (CompareManager.getAttempt()<NUM_OF_ATTEMPTS) {
            count=0;
            while (count < NUM_OF_PIECES) {

                System.out.println("Hadej barvy");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                String input = br.readLine();
                inputChar = input.charAt(0);
                System.out.println(inputChar);
                if (input.length() > 1) {
                    System.out.println("Lze zadat jen jedno znamenko");
                } else if (colorList.contains(Color.parse(inputChar))) {

                    inputArr[count] = Color.parse(inputChar);
                    count++;

                }
            }

            comparator.compare(inputArr);

            // TODO na priste poresit to jak spatne by se vas kod testoval,
            // napr jak byste testoval metodu compare?
            // chtelo by to aby metoda compare vracela nejaky objekt s vysledkem
            // a az zde v mainu by se vysledek vypsal do konzole = zadnej print v compare
            // koncept toho jak by to mohlo bejt
            CompareResult cr = comparator.compare(inputArr);

        showCompareResult(cr);
            // CompareResult by se pak dal testovat a oddelite logiku programu od prezentace(vypisovani do konzole)

        }



    }

    private static void showCompareResult(CompareResult cr) {
        System.out.println("Trefil jsi " + cr.getMatchColors() + " barev " + " a " + cr.getMatchPositions() + " presnych pozic");
        if(cr.getMatchColors()==4) {
            System.out.println("You are the mastermind!");
            System.exit(5);
        }else if(cr.isFinalAttempt()){
            System.out.println("Prohral jsi!");
            System.exit(5);
        }
    }

}


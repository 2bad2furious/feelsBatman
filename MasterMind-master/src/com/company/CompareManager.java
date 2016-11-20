package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub Janou�ek on 24. 11. 2015.
 */
public class CompareManager {

    private static Treasure myTreasure;
    private static int matchColors;
    private static int matchPositions;
    private static int attempt = 0;

    public CompareManager(Treasure myTreasure) {
        this.myTreasure = myTreasure;
    }

    public static int getAttempt() {
        return attempt;
    }

    public CompareResult compare(Color inputArr[]){
        matchColors = 0;
        matchPositions=0;

        for (int i = 0; i < inputArr.length; i++) {

            if (myTreasure.getColorList().get(i) == inputArr[i]) {
                matchPositions++;
            }
        }
            List<Integer> alreadyMatch = new ArrayList<Integer>();

        for (Color color : myTreasure.getColorList()){
            for (int i=0; i<inputArr.length;i++){
                if (color==inputArr[i] && !alreadyMatch.contains(i)){
                    alreadyMatch.add(i);
                    matchColors++;
                    break;
                }
            }
        }

        //matchColors-=matchPositions; ?????

        if (matchPositions != 4){
            attempt++;
        }

    return new CompareResult(matchColors,matchPositions,(attempt>=10));
    }



        }


//    public CompareManager(String color1, String color2, String color3, String color4, Treasure myTreasure) {
//        this.myTreasure = myTreasure;
//        this.attempt = attempt;
//        this.Logic();
//
//    }
//
//    public static void Logic(){
//       String userInput[] = new String[] {color1,color2,color3,color4};
//       String mind [] = new String[]{myTreasure.getColor1(),myTreasure.getColor2(),myTreasure.getColor3(), myTreasure.getColor4()};
//
//        for (int i = 0; i<userInput.length; i++){
//
//          if (userInput[i] == mind [i]){
//              matchPositions++;
//          }
//        }
//        List<Integer> alreadyMatch = new ArrayList<Integer>();
//
//        for (String color : mind){
//            for (int i=0; i<userInput.length;i++){
//                if (color==userInput[i] && !alreadyMatch.contains(i)){
//                    alreadyMatch.add(i);
//                    matchColors++;
//                    break;
//                }
//            }
//        }
//
//        matchColors-=matchPositions;
//
//        System.out.println("Trefil jsi " + matchColors + " barev " + " a " + matchPositions + " presnych pozic");
//
//        if (matchPositions == 4){
//            System.out.println("You are the mastermind!");
//            System.exit(5);
//        }else {
//            attempt++;
//        }
//
//        if (attempt>=10){
//            System.out.println("Prohral jsi!");
//            System.exit(5);
//      }
//
//    }



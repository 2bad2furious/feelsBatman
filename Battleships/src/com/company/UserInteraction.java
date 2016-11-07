package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 27.10.2016.
 */
public class UserInteraction {
    private static Scanner sc = new Scanner(System.in);

    public static void com(String a){
        System.out.println(a);
    }

    public static void comInnLine(String a){
        System.out.print(a);
    }

    public static Coordinates InitializeStartOfHumanRound(BattleField bf){
        System.out.println("It's your turn! :)");
        bf.printEnemyField();
        System.out.println("Insert first index");
        int a = sc.nextInt();
        System.out.println("Insert second index");
        int b = sc.nextInt();

        if(a>= bf.getSizeX()||b>= bf.getSizeY()){
            System.out.println("Wrong index:( insert only numbers smaller than "+bf.getSizeY());
            return InitializeStartOfHumanRound(bf);
        }

        return new Coordinates(a,b);
    }


    public static void reportStartOfBattle(ArrayList<Player> players,int i){
        System.out.println("It's on!");
        System.out.println((players.get(0).getName()+" starts with "+players.get(0).getBf().getCounter()+" ships"));
        System.out.println(players.get(1).getName()+" starts with "+players.get(1).getBf().getCounter()+" ships");
        System.out.println("Player "+players.get(i).getName()+" starts!");
    }

    public static void reportUserHit(int hit,int i){
        if(hit == 1){
            System.out.println("Target hit");
        }else if(hit == 0){
            System.out.println("Splash :(");
        }else if(hit == -1){
            System.out.println("You've already sunk that.");
        }else if(hit == -3){
            System.out.println("Wow, ship destoryed. Gratz");
        }

        System.out.println("You still have "+i+" left to sink.");

    }

    public static void reportEnemyHit(int hit,int i){
        if(hit == 1){
            System.out.println("You've been hit");
        }else if(hit == 0){
            System.out.println("The bot missed, phew");
        }else if(hit==-3){
            System.out.println("Dammit, he destroyed one of your ships.");
        }else{
            System.out.println("The bot is stupid, somebody should probably fix it. Not me though");
        }
        System.out.println("You still have "+i+" left. :)");
    }

    public static String askForName(){
        System.out.println("Insert your name");
        return sc.nextLine();
    }

    public static void reportSuccessOfPlayerAdding(String name){
        System.out.println("Player added, name: "+name);
    }

    public static int listAvailableShipTypes(ArrayList<Ship> options){
        System.out.println("Select the ship you want to place now :)");
        String[] arr = new String[options.size()];
        int i = 0;
        for(Ship s : options){
            arr[i] = s.getName();
            i++;
        }
        UserInteraction.listOptions(arr);
        i = sc.nextInt();
        if(i<options.size())
        return i;
        else {
            UserInteraction.reportWrongOption();
            return listAvailableShipTypes(options);
        }
         }

    public static void listOptions(String[] options){
        for(int i = 0;i<options.length;i++){
            System.out.println(i+": "+options[i]);
        }
    }

    public static void reportWrongOption(){
        System.out.println("Wrong option, choose again.");
    }

    public static void printUserField(Shippart[][] arr){
        String str = "  ";
        for (int i = 0; i < arr.length; i++) {
            str+= i;
        }
        System.out.println(str);
        for (int i = 0; i < arr.length; i++) {
            UserInteraction.comInnLine(i + " ");
            for (int j = 0; j < arr[0].length; j++) {
                //TODO <----> or ^-- atd
                UserInteraction.comInnLine(arr[i][j] == null ? "~" : ((arr[i][j].getParent().getRotated()==1) ? "^" : ">"));
            }
            System.out.println("");
        }
    }

    public static void printEnemyField(Shippart[][] arr){
        String str = "  ";
        for (int i = 0; i < arr.length; i++) {
            str+= i;
        }
        System.out.println(str);
        //TODO <----> or ^-- atd
        for (int i = 0; i < arr.length; i++) {
            UserInteraction.comInnLine(i + " ");
            for (int j = 0; j < arr[0].length; j++) {
                //if je to ship or voda nebo damaged or sunk
                System.out.print((arr[i][j]instanceof DamagedShippart)?"✝":"?");
            }
            System.out.println("");
        }
    }

    public static Coordinates askForCoordinates(){
        System.out.println("Please insert coordinates, one at a time");
        return new Coordinates(sc.nextInt(),sc.nextInt());
    }

    public static int askForRotation(){
        System.out.println("Do you want to rotate the ship?");
        UserInteraction.listOptions(new String[]{"No","Yes"});
        int i = sc.nextInt();
        if(i < 2)
            return i;
        else{
            UserInteraction.reportWrongOption();
            return askForRotation();
        }
    }

    public static void reportWrongCoordinates(){
        System.out.println("Invalid coordinates, please insert coordinates inside of the battlefield");
    }

    public static void reportSuccessOfShipAdding(int counter,int i){
        System.out.println("Successfully added");
        System.out.println("Number of ships on your field:" + counter);
        System.out.println("Number of ships needed on your field: " +i);
    }

    public static void reportFailOfShipAdding(){
        System.out.println("Already used index.");
    }

    public static void reportEnd(){
        System.out.println("The END");
    }

    public static void reportUserWin(){
        System.out.println("You've won!!! YAY");
    }

    public static void reportUserLoss(){
        System.out.println("You've lost :( Better luck next time :-----}");
    }

}

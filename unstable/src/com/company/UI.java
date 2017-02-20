package com.company;

import com.company.domain.model.Coordinates;
import com.company.domain.model.GameField;
import com.company.domain.model.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 23.01.2017.
 */
public class UI {
    private static Scanner sc = new Scanner(System.in);

    public static Coordinates getNextMove(){
        return new Coordinates(sc.nextInt(),sc.nextInt());
    }

    public static void print(List<List<GameField>> arr,Player currentPlayer, int p1count, int p2count){
        System.out.println("Current player:"+getPlayerName(currentPlayer));
        printField(arr);
        System.out.println("P1:"+p1count+"    P2:"+p2count);
    }
    public static void printField(List<List<GameField>> arr){
        arr.forEach((o)->{
            o.forEach((o1)->{
                System.out.print(o1.atomCount()+(getPlayerName(o1.player()))+" ");
            });
            System.out.println();
        });
    }
    private static String getPlayerName(Player player){
        if(player == Player.FIRST_PLAYER){
            return "P1";
        }else if(player == Player.SECOND_PLAYER){
            return "P2";
        }else{
            return "NO";
        }
    }

    public static void printGG(Player player){
        System.out.println(getPlayerName(player)+"won!!!");
        System.out.println("GG");
    }
}

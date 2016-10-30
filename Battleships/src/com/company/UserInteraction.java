package com.company;

import java.util.Scanner;

/**
 * Created by user on 27.10.2016.
 */
public class UserInteraction {
    private static Scanner sc = new Scanner(System.in);

    public static String askForLine(){
        return sc.nextLine();
    }

    public static String askForWord(){
        return sc.next();
    }

    public static void com(String a){
        System.out.println(a);
    }

    public static void comInnLine(String a){
        System.out.print(a);
    }
}

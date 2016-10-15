package com.company.Communication;

import java.util.Scanner;

/**
 * Created by user on 10.10.2016.
 */
public class AskFor {
    private static Scanner sc = new Scanner(System.in);

    public static int i(){
        if(sc.hasNext())
            return sc.nextInt();
        return 0;
    }

    public static String s(){
        if(sc.hasNext())
            return sc.next();
                return "";
    }

    public static String l(){
        if(sc.hasNext())
            return sc.nextLine();
        return "";
    }

}

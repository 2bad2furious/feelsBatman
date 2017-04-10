package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(factorialOf(5040));
    }

    private static int factorialOf(int a){
        int i,j = a;
        for(i = 2;j != 1;i++){
            if(a % i != 0) return -1;
            j /= i;
        }
        return i-1;
    }
}

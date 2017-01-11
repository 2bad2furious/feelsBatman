package com.company;

/**
 * Created by user on 10.01.2017.
 */
public class Day1{
    public static int solve() {
        String[] arr = getInput().split(", ");
        int goneNorth = 0;
        int goneEast = 0;
        int dir = 1; //1 - north,2 - east, 3 - south, 4 - west
        int goLength = 0;
        for (String str : arr) {
            if (str.charAt(0) == 'R') {
                dir++;
                if (dir == 5) dir = 1;
            } else {
                dir--;
                if (dir == 0) dir = 4;
            }
            goLength = Integer.parseInt(str.substring(1));
            switch (dir) {
                case 1: {
                    goneNorth += goLength;
                    break;
                }
                case 2: {
                    goneEast += goLength;
                    break;
                }
                case 3: {
                    goneNorth -= goLength;
                    break;
                }
                case 4: {
                    goneEast -= goLength;
                    break;
                }
            }
            System.out.println("goneNorth: " + goneNorth + " goneEast: " + goneEast + " dir: " + dir + " loc: " + str);
        }
        return Math.abs(goneNorth) + Math.abs(goneEast);
    }

    private static String getInput() {
        return "R1, L3, R5, R5, R5, L4, R5, R1, R2, L1, L1, R5, R1, L3, L5, L2, R4, L1, R4, R5, L3, R5, L1, R3, L5, R1, L2, R1, L5, L1, R1, R4, R1, L1, L3, R3, R5, L3, R4, L4, R5, L5, L1, L2, R4, R3, R3, L185, R3, R4, L5, L4, R48, R1, R2, L1, R1, L4, L4, R77, R5, L2, R192, R2, R5, L4, L5, L3, R2, L4, R1, L5, R5, R4, R1, R2, L3, R4, R4, L2, L4, L3, R5, R4, L2, L1, L3, R1, R5, R5, R2, L5, L2, L3, L4, R2, R1, L4, L1, R1, R5, R3, R3, R4, L1, L4, R1, L2, R3, L3, L2, L1, L2, L2, L1, L2, R3, R1, L4, R1, L1, L4, R1, L2, L5, R3, L5, L2, L2, L3, R1, L4, R1, R1, R2, L1, L4, L4, R2, R2, R2, R2, R5, R1, L1, L4, L5, R2, R4, L3, L5, R2, R3, L4, L1, R2, R3, R5, L2, L3, R3, R1, R3";
    }
}

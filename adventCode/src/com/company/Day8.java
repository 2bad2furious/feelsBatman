package com.company;

import java.util.stream.IntStream;

/**
 * Created by user on 10.01.2017.
 */
public class Day8 {
    public static int solve() {
        String[] s, s2;
        char[][] arr = new char[6][50];
        String cmd;
        char remember, remember2;
        int length, index;
        for (String str : getInput().split("\n")) {
            s = str.split(" ");
            cmd = s[0];
            if (cmd.equals("rect")) {
                s2 = s[1].split("x");
                for (int i = 0; i < Integer.parseInt(s2[1]); i++) {
                    for (int j = 0; j < Integer.parseInt(s2[0]); j++) {
                        arr[i][j] = '#';
                    }
                }
            } else if (cmd.equals("rotate")) {
                cmd = s[1];
                index = Integer.parseInt(s[2].split("=")[1]);
                length = Integer.parseInt(s[s.length - 1]);
                remember = ' ';
                remember2 = ' ';
                if (cmd.equals("column")) {
                    for (int i = 0; i < length; i++) {
                        remember = arr[0][index];
                        arr[0][index] = arr[arr.length - 1][index];
                        for (int j = 1; j < arr.length; j++) {
                            remember2 = arr[j][index];
                            arr[j][index] = remember;
                            remember = remember2;
                        }
                    }
                } else if (cmd.equals("row")) {
                    for (int j = 0; j < length; j++) {
                        remember = arr[index][0];
                        arr[index][0] = arr[index][arr[index].length - 1];
                        for (int i = 1; i < arr[index].length; i++) {
                            remember2 = arr[index][i];
                            arr[index][i] = remember;
                            remember = remember2;
                        }
                    }
                }
            }
        }
        printArr(arr);
        int counter = 0;
        for (char[] carr : arr) {
            for (char c : carr) {
                if (c != '\u0000')
                    counter++;
            }
        }
        return counter;
    }

    private static void printArr(char[][] arr) {
        for (char[] arr2 : arr) {
            for (char c : arr2) {
                System.out.print(":");
                System.out.print(((c == '\u0000') ? " " : 'x'));
                System.out.print("");
            }
            System.out.println();
        }
        System.out.println();
        IntStream.range(0, 50).forEach((a) -> System.out.print("-"));
        System.out.println();
    }

    private static String getInput() {
        return "rect 1x1\n" +
                "rotate row y=0 by 7\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 5\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 5\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 2\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 3\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 5\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 3\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 2\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 3\n" +
                "rect 2x1\n" +
                "rotate row y=0 by 7\n" +
                "rect 6x1\n" +
                "rotate row y=0 by 3\n" +
                "rect 2x1\n" +
                "rotate row y=0 by 2\n" +
                "rect 1x2\n" +
                "rotate row y=1 by 10\n" +
                "rotate row y=0 by 3\n" +
                "rotate column x=0 by 1\n" +
                "rect 2x1\n" +
                "rotate column x=20 by 1\n" +
                "rotate column x=15 by 1\n" +
                "rotate column x=5 by 1\n" +
                "rotate row y=1 by 5\n" +
                "rotate row y=0 by 2\n" +
                "rect 1x2\n" +
                "rotate row y=0 by 5\n" +
                "rotate column x=0 by 1\n" +
                "rect 4x1\n" +
                "rotate row y=2 by 15\n" +
                "rotate row y=0 by 5\n" +
                "rotate column x=0 by 1\n" +
                "rect 4x1\n" +
                "rotate row y=2 by 5\n" +
                "rotate row y=0 by 5\n" +
                "rotate column x=0 by 1\n" +
                "rect 4x1\n" +
                "rotate row y=2 by 10\n" +
                "rotate row y=0 by 10\n" +
                "rotate column x=8 by 1\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 9x1\n" +
                "rotate column x=27 by 1\n" +
                "rotate row y=0 by 5\n" +
                "rotate column x=0 by 1\n" +
                "rect 4x1\n" +
                "rotate column x=42 by 1\n" +
                "rotate column x=40 by 1\n" +
                "rotate column x=22 by 1\n" +
                "rotate column x=17 by 1\n" +
                "rotate column x=12 by 1\n" +
                "rotate column x=7 by 1\n" +
                "rotate column x=2 by 1\n" +
                "rotate row y=3 by 10\n" +
                "rotate row y=2 by 5\n" +
                "rotate row y=1 by 3\n" +
                "rotate row y=0 by 10\n" +
                "rect 1x4\n" +
                "rotate column x=37 by 2\n" +
                "rotate row y=3 by 18\n" +
                "rotate row y=2 by 30\n" +
                "rotate row y=1 by 7\n" +
                "rotate row y=0 by 2\n" +
                "rotate column x=13 by 3\n" +
                "rotate column x=12 by 1\n" +
                "rotate column x=10 by 1\n" +
                "rotate column x=7 by 1\n" +
                "rotate column x=6 by 3\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=3 by 3\n" +
                "rotate column x=2 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 14x1\n" +
                "rotate column x=38 by 3\n" +
                "rotate row y=3 by 12\n" +
                "rotate row y=2 by 10\n" +
                "rotate row y=0 by 10\n" +
                "rotate column x=7 by 1\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=2 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 9x1\n" +
                "rotate row y=4 by 20\n" +
                "rotate row y=3 by 25\n" +
                "rotate row y=2 by 10\n" +
                "rotate row y=0 by 15\n" +
                "rotate column x=12 by 1\n" +
                "rotate column x=10 by 1\n" +
                "rotate column x=8 by 3\n" +
                "rotate column x=7 by 1\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=3 by 3\n" +
                "rotate column x=2 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 14x1\n" +
                "rotate column x=34 by 1\n" +
                "rotate row y=1 by 45\n" +
                "rotate column x=47 by 1\n" +
                "rotate column x=42 by 1\n" +
                "rotate column x=19 by 1\n" +
                "rotate column x=9 by 2\n" +
                "rotate row y=4 by 7\n" +
                "rotate row y=3 by 20\n" +
                "rotate row y=0 by 7\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=3 by 1\n" +
                "rotate column x=2 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 6x1\n" +
                "rotate row y=4 by 8\n" +
                "rotate row y=3 by 5\n" +
                "rotate row y=1 by 5\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=4 by 1\n" +
                "rotate column x=3 by 2\n" +
                "rotate column x=2 by 1\n" +
                "rotate column x=1 by 3\n" +
                "rotate column x=0 by 1\n" +
                "rect 6x1\n" +
                "rotate column x=36 by 3\n" +
                "rotate column x=25 by 3\n" +
                "rotate column x=18 by 3\n" +
                "rotate column x=11 by 3\n" +
                "rotate column x=3 by 4\n" +
                "rotate row y=4 by 5\n" +
                "rotate row y=3 by 5\n" +
                "rotate row y=2 by 8\n" +
                "rotate row y=1 by 8\n" +
                "rotate row y=0 by 3\n" +
                "rotate column x=3 by 4\n" +
                "rotate column x=0 by 4\n" +
                "rect 4x4\n" +
                "rotate row y=4 by 10\n" +
                "rotate row y=3 by 20\n" +
                "rotate row y=1 by 10\n" +
                "rotate row y=0 by 10\n" +
                "rotate column x=8 by 1\n" +
                "rotate column x=7 by 1\n" +
                "rotate column x=6 by 1\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=3 by 1\n" +
                "rotate column x=2 by 1\n" +
                "rotate column x=1 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 9x1\n" +
                "rotate row y=0 by 40\n" +
                "rotate column x=44 by 1\n" +
                "rotate column x=35 by 5\n" +
                "rotate column x=18 by 5\n" +
                "rotate column x=15 by 3\n" +
                "rotate column x=10 by 5\n" +
                "rotate row y=5 by 15\n" +
                "rotate row y=4 by 10\n" +
                "rotate row y=3 by 40\n" +
                "rotate row y=2 by 20\n" +
                "rotate row y=1 by 45\n" +
                "rotate row y=0 by 35\n" +
                "rotate column x=48 by 1\n" +
                "rotate column x=47 by 5\n" +
                "rotate column x=46 by 5\n" +
                "rotate column x=45 by 1\n" +
                "rotate column x=43 by 1\n" +
                "rotate column x=40 by 1\n" +
                "rotate column x=38 by 2\n" +
                "rotate column x=37 by 3\n" +
                "rotate column x=36 by 2\n" +
                "rotate column x=32 by 2\n" +
                "rotate column x=31 by 2\n" +
                "rotate column x=28 by 1\n" +
                "rotate column x=23 by 3\n" +
                "rotate column x=22 by 3\n" +
                "rotate column x=21 by 5\n" +
                "rotate column x=20 by 1\n" +
                "rotate column x=18 by 1\n" +
                "rotate column x=17 by 3\n" +
                "rotate column x=13 by 1\n" +
                "rotate column x=10 by 1\n" +
                "rotate column x=8 by 1\n" +
                "rotate column x=7 by 5\n" +
                "rotate column x=6 by 5\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=3 by 5\n" +
                "rotate column x=2 by 5\n" +
                "rotate column x=1 by 5";
    }

    private static String getAltInput() {
        return "rect 3x2\n" + "rotate column x=1 by 1\n" + "rotate row y=0 by 4\n" + "rotate column x=1 by 1";
    }
}

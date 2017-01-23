package com.company;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by user on 16.01.2017.
 */
public class Day19 {
    public static int solve() {
        ArrayList<Elf> arr = Elf.getList(Integer.parseInt(getInput()));
        System.out.println(arr.size());
        while (true) {
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getCount() == 0)
                    arr.get(i).steal();

                if (!arr.get(i).isBlocked()) {
                    arr.get(i).add(getNext(arr, i).steal());
                }
            }
            if (arr.stream().filter((o) -> !o.isBlocked()).count() <= 1) {
                break;
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            if(!arr.get(i).isBlocked())
                return i+1;
        }
        throw new IllegalArgumentException();
    }

    private static Elf getNext(ArrayList<Elf> arr, int index) {
        for (int i = index+1; i < arr.size(); i++) {
            if (!arr.get(i).isBlocked())
                return arr.get(i);
        }
        for (int i = 0; i < index; i++) {
            if (!arr.get(i).isBlocked())
                return arr.get(i);
        }
        throw new IllegalArgumentException();
    }

    private static String getInput() {
        return "3001330";
    }
}

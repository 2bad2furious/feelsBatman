package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 10.01.2017.
 */
public class Day5 {
    public static String solve() throws NoSuchAlgorithmException {
        String pass = "", hash = "";
        String id = getInput();
        int counter = 7777889;
        MessageDigest m = MessageDigest.getInstance("MD5");
        while (pass.length() < 8) {
            m.update((id + counter).getBytes());
            byte[] ba = m.digest();
            StringBuilder hex = new StringBuilder(ba.length * 2);
            for (byte b : ba)
                hex.append(String.format("%02X", b));
            hash = hex.toString();

            if (counter % 100000 == 0)
                System.out.println((id + counter) + " " + hash + " " + pass);

            if (hash.substring(0, 5).equals("00000")) {
                pass += hash.charAt(5);
                System.out.println((id + counter) + " " + hash + " " + pass);
            }
            counter++;
        }

        return "";
    }
    private static String getInput() {
        return "abbhdwsy";
    }
}

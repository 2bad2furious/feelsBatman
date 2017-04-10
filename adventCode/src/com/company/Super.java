package com.company;

public class Super {

    public Super() {
        overrideMe();
    }

    public void overrideMe() {

    }
}
final class Sub extends Super {
    private final Date date; // Blank final, set by constructor

    Sub() {
        date = new Date();
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}

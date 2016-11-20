package com.company;

/**
 * Created by Jakub Janoušek on 24. 11. 2015.
 */
public enum Color {

    RED("cervena", 'R'),
    BLUE("modra", 'B'),
    YELLOW("zluta", 'Y'),
    GREEN("zelena", 'G'),
    PINK("ruzova", 'P'),
    ORANGE("oranzova", 'O');

    private final String name;
    public final char symbol;

    Color(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }

   public String toString(){
       return this.name;
   }

    public static Color parse(char c) {
        for (Color color : Color.values()){
            if (color.symbol == c) {
                return color;
            }
        }
        throw new IllegalArgumentException("Non existing color for this char");
    }

}

package com.company;

import java.util.List;

/**
 * Created by Jakub Janoušek on 24. 11. 2015.
 */
public class Treasure {
    private final List<Color> colorList;

    public Treasure(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<Color> getColorList() {
        return colorList;
    }
}

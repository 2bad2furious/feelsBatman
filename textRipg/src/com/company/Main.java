package com.company;

import com.company.domain.Item;
import com.company.domain.Location;
import com.company.domain.Player;
import com.company.domain.impl.LocationImpl;
import com.company.ui.GameUI;

public class Main {

    public static void main(String[] args) {
        Location startingLocation = createGame();
        GameUI gameUI = new GameUI();
        gameUI.play(startingLocation);
    }

    private static Location createGame() {
        Location startingLocation = new LocationImpl("First location");
        Location leftLocation = new LocationImpl("You are in the left room");
        Location rightLocation = new LocationImpl("You are in the right room");
        startingLocation.addOption("Turn left", leftLocation);
        startingLocation.addOption("Turn right", rightLocation);
        leftLocation.addOption("Go back",startingLocation);
        leftLocation.addOption("Item1",new Item("Lopata","lopata"));
        rightLocation.addOption("Go back",startingLocation);
        return startingLocation;
    }
}

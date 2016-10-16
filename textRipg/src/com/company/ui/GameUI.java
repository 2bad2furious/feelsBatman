package com.company.ui;

import com.company.Communication.AskFor;
import com.company.Communication.Response;
import com.company.domain.Item;
import com.company.domain.Location;
import com.company.domain.Option;
import com.company.domain.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameUI {
    private ArrayList<Player> players;

    public GameUI(){
        players = new ArrayList<Player>();
        this.players.add(new Player("MyName"));
    }

    public void play(Location location) {
        Item armor = players.get(0).getArmor();
        if(armor != null)
        Response.print(armor.getTitle());
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        presentLocation(location);
        if (location.getOptions() != null && location.getOptions().size() > 0) {
            Option selectedOption = askForMove(location);
            if (selectedOption.getLocation() != null)
                play(selectedOption.getLocation());
            else if (selectedOption.getItem() != null) {
                Player p1 = players.get(0);
                Item item = p1.addToInventory(selectedOption.getItem());
                location.removeOption(selectedOption);
                if(item != null)
                    location.addOption(item.getTitle(),item);
                    play(location);
                   }else
                play(null);
        } else {
            System.out.println("The End");
        }
    }

    private void presentLocation(Location location) {
        System.out.println(location.getText());
        int index = 1;
        for (Option option : location.getOptions()) {
            System.out.println(index + ") " + option.getText());
            index++;
        }
    }

    private Option askForMove(Location location) {
        Response.print("Vyber z následujících možností");
        int index = AskFor.i();
        try {
            return location.getOptions().get(index - 1);
        } catch (Exception ex) {
            Response.print("Wrong index");
            return askForMove(location);
        }
    }
}

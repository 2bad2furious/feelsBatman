package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 05.12.2016.
 */
public class Game {
    private Field field;

    public Game(int rows, int columns, int mines) {

        field = new Field(rows, columns, mines);

        boolean result = startRound();

        if (result) UI.reportWin();
        else UI.reportMineExplosion();

        UI.printField(field.getList());

        System.exit(1);
    }

    private boolean startRound() {
        UI.printField(field.getList());
        String s = UI.askForLoc();
        if (s.equals("help") || s.equals("?")) UI.printHelp();
            //TODO fix this code
        else if (isCmdValid(s)) {
            String[] string = s.split(" ");
            int p1 = Integer.parseInt(string[0]);
            int p2 = Integer.parseInt(string[1]);
            String cmd = string[2];
            if (field.isRevealed(p1, p2)) {
                UI.reportAlreadyRevealed();
                return startRound();
            }
            if (cmd.equals("r") || cmd.equals("reveal")) {

                if (field.isFlagged(p1, p2)) UI.reportFlaggedIndex();
                else if (field.reveal(p1, p2) == -1) return false; //Lose

            } else if (cmd.equals("q") || cmd.equals("question")) {
                field.question(p1, p2);
            } else if (cmd.equals("f") || cmd.equals("flag")) {
                field.flag(p1, p2);
            } else throw new IllegalArgumentException("Something happened");

            if (field.checkForAnythingButBombNotRevealed()) return true; //Win

        } else {
            UI.reportWrongFormat();
            return startRound();
        }
        return startRound();
    }

    private boolean isCmdValid(String s) {
        try {
            String[] cmd = s.split(" ");
            if (cmd.length != 3) return false;
            if (!field.isOnField(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]))) return false;
            cmd[2] = cmd[2].toLowerCase();
            if (!cmd[2].equals("reveal")
                    && !cmd[2].equals("r")
                    && !cmd[2].equals("flag")
                    && !cmd[2].equals("f")
                    && !cmd[2].equals("question")
                    && !cmd[2].equals("q")
                    ) return false;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}

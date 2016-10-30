package com.company;

/**
 * Created by user on 27.10.2016.
 */
public class BattleField {
    private int[][] field;
    private int sizeX;
    private int sizeY;
    private int limit;
    private int shipCounter;

    public BattleField(int[][] arr, int sizeX, int sizeY, int limit) {
        int counter = 0;
        int counter2 = 0;
        if (arr != null) {
            for (int i = 0; i < arr[0].length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    counter2++;
                    if (arr[i][j] == 1) {
                        counter++;
                    }
                }
            }
        } else {
            arr = new int[sizeX][sizeY];
            this.limit = limit;
            counter = 0;
            while (true) {
                this.field = arr;
                printField();
                UserInteraction.com("Insert first index");
                int a = Integer.parseInt(UserInteraction.askForWord());
                UserInteraction.com("Insert second index");
                int b = Integer.parseInt(UserInteraction.askForWord());
                if(a>=arr.length||b>= arr[0].length){
                    UserInteraction.com("Wrong index:( insert only numbers smaller than "+(sizeX));
                    continue;
                }
                if (arr[a][b] != 1) {
                    arr[a][b] = 1;
                    counter++;
                    UserInteraction.com("Successfully added");
                    UserInteraction.com("Number of ships on your field:" + counter);
                    UserInteraction.com("Number of ships needed on your field:" + limit);
                } else {
                    UserInteraction.com("Already used index!");
                    continue;
                }
                if (counter == this.limit) {
                    break;
                }
            }
        }
        this.sizeX = arr[0].length;
        this.sizeY = arr.length;
        field = arr;
        shipCounter = counter;
    }

    public int getCounter(){
        return this.shipCounter;
    }

    public int hit(int x, int y){
                int i = this.field[x][y];
        if(i==1) {
            shipCounter--;
            this.field[x][y] = -1;
        }else{
            this.field[x][y] = -2;
        }
        return i;
    }



    public int getSizeX(){
        return this.sizeX;
    }

    public int getSizeY(){
        return this.sizeY;
    }

    private void printField() {
        String str = " ";
        for (int i = 0; i < this.field.length; i++) {
            str+= i;
        }
        for (int i = 0; i < this.field.length; i++) {
            UserInteraction.comInnLine(i + " ");
            for (int j = 0; j < this.field[0].length; j++) {
                UserInteraction.comInnLine(this.field[i][j] == 0 ? "~" : ">");
            }
            UserInteraction.com("");
        }
    }
    public void printEnemyField(){
        String str = " ";
        for (int i = 0; i < this.field.length; i++) {
            str+= i;
        }
        for (int i = 0; i < this.field.length; i++) {
            UserInteraction.comInnLine(i + " ");
            for (int j = 0; j < this.field[0].length; j++) {
                if(this.field[i][j]==0 || this.field[i][j]==1){
                    UserInteraction.comInnLine("?");
                }else if(this.field[i][j] == -1){
                    UserInteraction.comInnLine("✝");
                }else{
                    UserInteraction.comInnLine("o");
                }
            }
            UserInteraction.com("");
        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by user on 05.12.2016.
 */
public class Field {
    private List<List<FieldValue>> field;
    private int bombCounter;

    public Field(List<List<FieldValue>> arr) {
        if (arr == null) throw new NullPointerException("arr cannot be null, call different constructor");
        if (checked(arr)) field = arr;
        else throw new IllegalArgumentException();
    }

    public Field(int size1, int size2, int count) {
        bombCounter = count;
        field = generate(size1, size2, count);
        UI.printField(field);
    }

    public int reveal(int x, int y) {
        int val = field.get(x).get(y).getVal();
        if (isRevealed(x, y)) return val;
        field.get(x).get(y).reveal();
        if (val == 0) getSurroundings(x, y)
                .filter(i -> i.isPresent())
                .forEach(i -> reveal(i.get().locx, i.get().locy));
        return val;
    }

    public List<List<FieldValue>> getList() {
        return field;
    }

    public void question(int x, int y) {
        field.get(x).get(y).question();
    }

    public void flag(int x, int y) {
        field.get(x).get(y).flag();
    }

    public boolean isFlagged(int x, int y) {
        return (field.get(x).get(y).status() == Status.FLAGGED);
    }

    public boolean isRevealed(int x, int y) {
        return (field.get(x).get(y).status() == Status.REVEALED);
    }

    public boolean checkForAnythingButBombNotRevealed() {
        int a = (int) field.stream()
                .flatMap(i -> i.stream())
                .filter(v -> v.status() != Status.REVEALED)
                .count();
        int b = bombCounter;

        return (a == b);
    }

    private List<List<FieldValue>> generate(int size1, int size2, int count) {
        List<List<FieldValue>> arr = new ArrayList<>();
        for (int i = 0; i < size1; i++) {
            arr.add(i, new ArrayList<>());
            for (int j = 0; j < size2; j++) {
                arr.get(i).add(j, new FieldValue(-1, -1, 0));
            }
        }

        Random rn = new Random();
        int num;
        int num2;

        while (count > 0) {
            num = Math.abs(rn.nextInt(size1));
            num2 = Math.abs(rn.nextInt(size2));


            if (arr.get(num).get(num2).locx == -1 && arr.get(num).get(num2).locy == -1) {
                arr.get(num).set(num2, new FieldValue(num, num2, -1));
                count--;
            }
        }

        field = arr;

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                if (arr.get(i).get(j).getVal() != -1) {
                    arr.get(i).set(j, new FieldValue(i, j, (
                            (int) getSurroundings(i, j)
                                    .filter(k -> k.isPresent())
                                    .filter(k -> k.get().getVal() == -1)
                                    .count())));
                }
            }

        }
        return arr;
    }

    private boolean checked(List<List<FieldValue>> arr) {
        bombCounter = (int) arr.stream()
                .flatMap(value -> value.stream())
                .filter(fieldValue -> fieldValue.getVal() == -1)
                .count();

        return arr.stream()
                .flatMap(value -> value.stream())
                .filter(fieldValue -> fieldValue.getVal() >= 0)
                .noneMatch(fieldValue -> fieldValue.getVal() !=
                        getSurroundings(fieldValue.locx, fieldValue.locy)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .filter(fieldValue1 -> fieldValue1.getVal() == -1).count()
                );
    }

    private Stream<Optional<FieldValue>> getSurroundings(int x, int y) {
        List<Optional<FieldValue>> result = new ArrayList<>();
        result.add(getOpt(x - 1, y + 1));
        result.add(getOpt(x - 1, y));
        result.add(getOpt(x - 1, y - 1));
        result.add(getOpt(x, y + 1));
        result.add(getOpt(x, y - 1));
        result.add(getOpt(x + 1, y + 1));
        result.add(getOpt(x + 1, y));
        result.add(getOpt(x + 1, y - 1));
        return result.stream();
    }

    public boolean isOnField(int x, int y) {
        return !(x >= field.size() || x < 0 || y < 0 || y >= field.get(x).size());
    }

    private Optional<FieldValue> getOpt(int x, int y) {
        return Optional.ofNullable((isOnField(x, y) ? field.get(x).get(y) : null));
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class Field {
    private List<List<FieldValue>> field;

    public Field(List<List<FieldValue>> arr) {
        if (arr == null) throw new NullPointerException("Argument arr needed");

        if (check(arr)) this.field = arr;
        else throw new IllegalArgumentException("Arr is not valid");
    }

    private boolean check(List<List<FieldValue>> arr) {
        return true;
    }

    private FieldValue get(int x, int y) {
        return get(x, y, false).orElseThrow(() -> new IndexOutOfBoundsException());
    }

    public void reveal(int x, int y) {
        reveal(x, y, false);

        GUI.printField(field);
    }

    private void reveal(int x, int y, boolean check) {
        if (isOnField(x, y)) {
            if (check) return;
            else throw new IllegalArgumentException("Invalid location");
        }

        get(x, y).reveal();

        int val = get(x, y).getValue();
        if (val == 0) {
            reveal(x - 1, y + 1, true);
            reveal(x - 1, y, true);
            reveal(x - 1, y - 1, true);
            reveal(x, y + 1, true);
            reveal(x, y - 1, true);
            reveal(x + 1, y + 1, true);
            reveal(x + 1, y, true);
            reveal(x + 1, y - 1, true);
        }
    }

    private Stream<Optional<FieldValue>> getSurroundings(int x, int y) {
        List<Optional<FieldValue>> result = new ArrayList<>();
        result.add(get(x - 1, y + 1, true));
        result.add(get(x - 1, y, true));
        result.add(get(x - 1, y - 1, true));
        result.add(get(x, y + 1, true));
        result.add(get(x, y - 1, true));
        result.add(get(x + 1, y + 1, true));
        result.add(get(x + 1, y, true));
        result.add(get(x + 1, y - 1, true));
        return result.stream();
    }

    private boolean isOnField(int x, int y) {
        return !(x >= field.size() || y >= field.get(x).size() || x < 0 || y < 0);
    }

    private Optional<FieldValue> get(int x, int y, boolean check) {
        if (!check) return Optional.of(field.get(x).get(y));
        else return Optional.of((isOnField(x, y)) ? field.get(x).get(y) : null);
    }
}

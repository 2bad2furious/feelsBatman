package com.company;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by user on 12.12.2016.
 */
public class AI {
    private static Random rn = new Random();

    public static String tryNewGuess(Field field) {
        if (!isAnythingRevealed(field)) {
            return revealRandom(field);
        } else {
            return evalField(field);
        }
    }

    private static String evalField(Field field) {
        Optional<FieldValue> o = field.getList().stream()
                .flatMap(Collection::stream)
                .filter(v -> v.getVal() == 1)
                .filter(v -> isCornerOne(field.getSurroundings(v.locx, v.locy)))
                .findFirst();

        if (o.isPresent()) {
            return flag(getCorner(field.getSurroundings(o.get().locx, o.get().locy)));
        } else {
            return revealRandom(field);
        }
    }

    private static boolean isAnythingRevealed(Field field) {
        return field.getList()
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(i -> i.status() == Status.REVEALED);
    }

    private static String revealRandom(Field field) {
        return reveal(new FieldValue(rn.nextInt(field.getList().size()), rn.nextInt(field.getList().get(0).size()), 0));
    }

    private static String flag(FieldValue fieldValue) {
        return (fieldValue.locx + " " + fieldValue.locy + " f");
    }

    private static String reveal(FieldValue fieldValue) {
        return (fieldValue.locx + " " + fieldValue.locy + " r");
    }

    private static boolean isCornerOne(Stream<Optional<FieldValue>> str) {
        List<Optional<FieldValue>> list = str.collect(Collectors.toList());
        if (list.get(1).isPresent() && list.get(3).isPresent() && list.get(1).get().getVal() > 0 && list.get(3).get().getVal() > 0)
            return true;
        else if (list.get(1).isPresent() && list.get(4).isPresent() && list.get(1).get().getVal() > 0 && list.get(4).get().getVal() > 0)
            return true;
        else if (list.get(4).isPresent() && list.get(6).isPresent() && list.get(4).get().getVal() > 0 && list.get(6).get().getVal() > 0)
            return true;
        else if (list.get(3).isPresent() && list.get(6).isPresent() && list.get(3).get().getVal() > 0 && list.get(6).get().getVal() > 0)
            return true;
        else return false;
    }

    private static FieldValue getCorner(Stream<Optional<FieldValue>> str) {
        List<Optional<FieldValue>> list = str.collect(Collectors.toList());
        if (list.get(1).isPresent() && list.get(3).isPresent() && list.get(1).get().getVal() > 0 && list.get(3).get().getVal() > 0)
            return list.get(0).get();
        else if (list.get(1).isPresent() && list.get(4).isPresent() && list.get(1).get().getVal() > 0 && list.get(4).get().getVal() > 0)
            return list.get(2).get();
        else if (list.get(4).isPresent() && list.get(6).isPresent() && list.get(4).get().getVal() > 0 && list.get(6).get().getVal() > 0)
            return list.get(7).get();
        else return list.get(5).get();
    }
}

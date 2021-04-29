package com.task7.office;

import java.util.List;
import java.util.Random;

public abstract class Utils {

    private static final Random index = new Random();

    public static <T> T randomChoice(List<T> collection) {
        int randomIndex = index.nextInt(collection.size());
        return collection.get(randomIndex);
    }

    public static int randomChoiceBound(int bound) {
        return (int) (1 + (Math.random() * bound));
    }
}

package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

public class ReplayingRandom extends Random {

    private final String[] fives;
    private final String[] nines;
    private int indexInFives;
    private int indexInNines;

    public ReplayingRandom(String five, String nine) {
        fives = five.split(",");
        nines = nine.split(",");
    }

    @Override
    public int nextInt(int n) {
        if (n == 5) {
            return getNextValueOf(fives, indexInFives++);
        } else if (n == 9) {
            return getNextValueOf(nines, indexInNines++);
        }
        throw new UnsupportedOperationException("not expected invocation of nextInt(" + n + ")");
    }

    private int getNextValueOf(String[] numbers, int index) {
        return Integer.parseInt(numbers[index]);
    }
}

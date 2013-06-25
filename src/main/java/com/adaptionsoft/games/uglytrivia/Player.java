package com.adaptionsoft.games.uglytrivia;

public class Player {
    private static final int NEEDED_COINS_TO_WIN = 6;

    private final String name;
    private int purse;

    public Player(String name) {
        this.name = name;
    }

    void answeredCorrect() {
        System.out.println("Answer was correct!!!!");
        purse++;
        System.out.println(name + " now has " + purse + " Gold Coins.");
    }

    boolean didPlayerNotWin() {
        return !(purse == NEEDED_COINS_TO_WIN);
    }

    @Override
    public String toString() {
        return name;
    }
}

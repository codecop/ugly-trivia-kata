package com.adaptionsoft.games.uglytrivia;

public class Player {
    private static final int NEEDED_COINS_TO_WIN = 6;

    private final String name;
    private final Place place = new Place();
    private boolean inPenaltyBox;

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

    public void advanceBy(int eyesOfDice) {
        place.advanceCurrentPlayerBy(eyesOfDice);
        System.out.println(name + "'s new location is " + place.getPlace());
    }

    public int getPlace() {
        return place.getPlace();
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    void goToPenaltyBox() {
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    // TODO add logic to reset penalty box - bug?
}

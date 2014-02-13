package com.adaptionsoft.games.uglytrivia;

public class Player {

    private static final int NEEDED_COINS_TO_WIN = 6;

    private final String name;
    private final Place place = new Place();

    private boolean inPenaltyBox;
    private static boolean gettingOutOfPenaltyBox; // TODO state is global not for each player? player can never come out of penalty box?
    private int purse;

    public Player(String name) {
        this.name = name;
    }

    public void answeredCorrect() {
        purse++;
        System.out.println(name + " now has " + purse + " Gold Coins.");
    }

    public boolean didPlayerNotWin() {
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

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void goToPenaltyBox() {
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    public void exitPenaltyBox() {
        // TODO add logic to reset penalty box - bug?
        throw new UnsupportedOperationException("not implemented, known defect, never called");
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOut) {
        gettingOutOfPenaltyBox = gettingOut;
        String maybe = "not ";
        if (gettingOutOfPenaltyBox) {
            maybe = "";
        }

        System.out.println(name + " is " + maybe + "getting out of the penalty box");
    }

    public boolean isGettingOutOfPenaltyBox() {
        return gettingOutOfPenaltyBox;
    }

    public String getName() {
        return name;
    }

    public Category currentCategory() {
        return place.currentCategory();
    }
}

package com.adaptionsoft.games.uglytrivia;

public class Player {

    private static final int NEEDED_COINS_TO_WIN = 6;

    private final PlayerUI showPlayer;

    private final String name;
    private final Place place = new Place();

    private int purse;
    private boolean inPenaltyBox;
    private static boolean gettingOutOfPenaltyBox; // TODO (not in scope) bug: State is global not for each player. Player can never come out of penalty box.

    public Player(String name, PlayerUI show) {
        this.name = name;
        this.showPlayer = show;
    }

    public String getName() {
        return name;
    }

    public Category currentCategory() {
        return place.currentCategory();
    }

    public void answeredCorrect() {
        purse++;
        showPlayer.moreMoney(name, purse);
    }

    public boolean didPlayerNotWin() {
        return !(purse == NEEDED_COINS_TO_WIN);
    }

    public void advanceBy(int eyesOfDice) {
        place.advanceCurrentPlayerBy(eyesOfDice);
        showPlayer.advanceToNewPlace(name, place.getPlace());
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void goToPenaltyBox() {
        showPlayer.goIntoPenaltyBox(name);
        inPenaltyBox = true;
    }

    public void exitPenaltyBox() {
        // TODO (not in scope) bug: Logic to reset penalty box is never called.
        throw new UnsupportedOperationException("not implemented, known defect, never called");
    }

    // TODO check coverage for potential to add tests
    public void setGettingOutOfPenaltyBox(boolean gettingOut) {
        gettingOutOfPenaltyBox = gettingOut;
        if (gettingOutOfPenaltyBox) {
            showPlayer.gettingOutOfPenaltyBox(name);
        } else {
            showPlayer.notGettingOutOfPenaltyBox(name);
        }
    }

    public boolean isGettingOutOfPenaltyBox() {
        return gettingOutOfPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }

    public void play(int eyesOfDice) {
        showPlayer.beginTurn(name, eyesOfDice);
    }
}

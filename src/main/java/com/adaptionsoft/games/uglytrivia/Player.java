package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.Game.UI;

public class Player {

    private static final int NEEDED_COINS_TO_WIN = 6;

    private final UI show;

    private final String name;
    private final Place place = new Place();

    private int purse;
    private boolean inPenaltyBox;
    private static boolean gettingOutOfPenaltyBox; // TODO (not in scope) bug: State is global not for each player. Player can never come out of penalty box.

    public Player(String name) {
        this(name, new UI()); // TODO get rid
    }
    
    public Player(String name, UI show) {
        this.name = name;
        this.show = show;
    }

    public String getName() {
        return name;
    }

    public Category currentCategory() {
        return place.currentCategory();
    }

    public void answeredCorrect() {
        purse++;
        show.playersGold(name, purse);        
    }

    public boolean didPlayerNotWin() {
        return !(purse == NEEDED_COINS_TO_WIN);
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
        // TODO (not in scope) bug: Logic to reset penalty box is never called.
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

    @Override
    public String toString() {
        return name;
    }
}

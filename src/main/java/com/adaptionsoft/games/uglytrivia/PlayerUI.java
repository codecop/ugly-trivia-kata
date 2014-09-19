package com.adaptionsoft.games.uglytrivia;

public class PlayerUI {

    private void print(String msg) {
        System.out.println(msg);
    }

    public void beginTurn(String name, int eyesOfDice) {
        print(name + " is the current player");
        print("They have rolled a " + eyesOfDice);
    }

    public void moreMoney(String name, int purse) {
        print(name + " now has " + purse + " Gold Coins.");
    }

    public void advanceToNewPlace(String name, int place) {
        print(name + "'s new location is " + place);
    }

    public void goIntoPenaltyBox(String name) {
        print(name + " was sent to the penalty box");
    }

    public void gettingOutOfPenaltyBox(String name) {
        maybeGettingOutOfPenaltyBox(name, "");
    }

    public void notGettingOutOfPenaltyBox(String name) {
        maybeGettingOutOfPenaltyBox(name, "not ");
    }

    private void maybeGettingOutOfPenaltyBox(String name, String maybe) {
        print(name + " is " + maybe + "getting out of the penalty box");
    }

}

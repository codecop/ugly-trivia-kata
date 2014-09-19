package com.adaptionsoft.games.uglytrivia;

public class UI {
    // TODO 2. split UI into GameShow and PlayerShow (maybe use same UI inside with System.out)
    // TODO 3. provide UI from outside using constructors
    // TODO create tests for ui, reuse (copy) test that capture (2 or 3)
    // TODO change test cases that capture to verify interaction

    private void print(String msg) {
        System.out.println(msg);
    }

    public void question(String category, String question) {
        print("The category is " + category);
        print(question);
    }

    public void correctAnswer() {
        print("Answer was correct!!!!");
    }

    public void wrongAnswer() {
        print("Question was incorrectly answered");
    }

    public void playerHasBeenAdded(String name, int size) {
        print(name + " was added");
        print("They are player number " + size);
    }

    // name is first, use PlayerUI?

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

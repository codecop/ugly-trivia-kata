package com.adaptionsoft.games.uglytrivia;

public class CurrentPlayer {

    private final Players players;
    private int currentPlayer;

    public CurrentPlayer(Players players) {
        this.players = players;
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public void goToPenaltyBox() {
        getCurrentPlayer().goToPenaltyBox();
    }

    public boolean isInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    public void answeredCorrect() {
        getCurrentPlayer().answeredCorrect();
    }

    public boolean didNotWin() {
        return getCurrentPlayer().didPlayerNotWin();
    }

    public void advanceBy(int eyesOfDice) {
        getCurrentPlayer().advanceBy(eyesOfDice);
    }

    public Category currentCategory() {
        return getCurrentPlayer().currentCategory();
    }

    public String getName() {
        return getCurrentPlayer().getName();
    }

    public boolean isGettingOutOfPenaltyBox() {
       return getCurrentPlayer().isGettingOutOfPenaltyBox();
    }

    public void willGetOutOfPenaltyBox() {
        getCurrentPlayer().setGettingOutOfPenaltyBox(true);
    }

    public void willStayInPenaltyBox() {
        getCurrentPlayer().setGettingOutOfPenaltyBox(false);
    }

    public void play(int eyesOfDice) {
        getCurrentPlayer().play(eyesOfDice);
    }
}

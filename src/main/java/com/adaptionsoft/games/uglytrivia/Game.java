package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions;
    private final Players players;

    public Game(Players players, Questions questions) {
        this.players = players;
        this.questions = questions;
    }

    public void playCurrentPlayer(int eyesOfDice) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + eyesOfDice);

        if (players.isCurrentPlayerInPenaltyBox()) {
            handleCurrentPlayerPenalty(eyesOfDice);
        } else {
            moveAndAskCurrentPlayerFor(eyesOfDice);
        }
    }

    private void handleCurrentPlayerPenalty(int eyesOfDice) {
        if (isOdd(eyesOfDice)) {
            getCurrentPlayer().setGettingOutOfPenaltyBox(true);
            moveAndAskCurrentPlayerFor(eyesOfDice);
        } else {
            getCurrentPlayer().setGettingOutOfPenaltyBox(false);
        }
    }

    private boolean isOdd(int eyesOfDice) {
        return eyesOfDice % 2 != 0;
    }

    private void moveAndAskCurrentPlayerFor(int eyesOfDice) {
        getCurrentPlayer().advanceBy(eyesOfDice);
        askQuestion();
    }

    private void askQuestion() {
        Category currentCategory = getCurrentPlayer().currentCategory();
        System.out.println("The category is " + currentCategory);
        System.out.println(questions.nextFor(currentCategory));
    }

    private Player getCurrentPlayer() {
        // TODO if this goes away, rename players to currentPlayer, remove xCurrentPlayer in all method names
        return players.getCurrentPlayer();
    }

    public boolean correctAnswer() {
        if (players.isCurrentPlayerInPenaltyBox()) {
            return correctAnswerInPenaltyBox();
        } else {
            return playerWinsCoin();
        }
    }

    private boolean correctAnswerInPenaltyBox() {
        if (getCurrentPlayer().isGettingOutOfPenaltyBox()) {
            return playerWinsCoin();
        } else {
            return playerDoesNotWinCoin();
        }
    }

    private boolean playerDoesNotWinCoin() {
        players.changeCurrentPlayer();
        return true;
    }

    private boolean playerWinsCoin() {
        System.out.println("Answer was correct!!!!");
        players.currentPlayerAnsweredCorrect();

        boolean notWinner = players.didCurrentPlayerNotWin();
        players.changeCurrentPlayer();

        return notWinner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        players.currentPlayerGoToPenaltyBox();

        return playerDoesNotWinCoin();
    }

    // TODO what is that boolean return value?
    // TODO restructure methods to be either high or low level so we can see the algorithm
    // TODO factor out/separate the output, have it in one place only
}

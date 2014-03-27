package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions;

    // TODO rename players to currentPlayer, remove xCurrentPlayer in all method names
    private final Players players;

    public Game(Players players, Questions questions) {
        this.players = players;
        this.questions = questions;
    }

    public void playCurrentPlayer(int eyesOfDice) {
        System.out.println(players.getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + eyesOfDice);

        if (players.isCurrentPlayerInPenaltyBox()) {
            handleCurrentPlayerPenalty(eyesOfDice);
        } else {
            moveAndAskCurrentPlayerFor(eyesOfDice);
        }
    }

    private void handleCurrentPlayerPenalty(int eyesOfDice) {
        if (isOdd(eyesOfDice)) {
            players.setGettingOutOfPenaltyBox(true);
            moveAndAskCurrentPlayerFor(eyesOfDice);
        } else {
            players.setGettingOutOfPenaltyBox(false);
        }
    }

    private boolean isOdd(int eyesOfDice) {
        return eyesOfDice % 2 != 0;
    }

    private void moveAndAskCurrentPlayerFor(int eyesOfDice) {
        players.advanceBy(eyesOfDice);
        askQuestion();
    }

    private void askQuestion() {
        Category currentCategory = players.currentCategory();
        System.out.println("The category is " + currentCategory.displayName());
        System.out.println(questions.nextFor(currentCategory));
    }

    public boolean correctAnswer() {
        if (players.isCurrentPlayerInPenaltyBox()) {
            return correctAnswerInPenaltyBox();
        } else {
            return playerWinsCoin(); // had typo corrent
        }
        // TODO what is that boolean return value?
    }

    private boolean correctAnswerInPenaltyBox() {
        if (players.isCurrentPlayerGettingOutOfPenaltyBox()) {
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

    // TODO restructure methods to be either high or low level so we can see the algorithm
    // TODO factor out/separate the output, have it in one place only
}

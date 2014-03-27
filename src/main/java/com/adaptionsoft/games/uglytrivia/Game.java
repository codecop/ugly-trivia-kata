package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions;
    private final Players currentPlayer;
    // TODO smell: Players class reads better as currentPlayer field.

    public Game(Players currentPlayer, Questions questions) {
        this.currentPlayer = currentPlayer;
        this.questions = questions;
    }

    public void play(int eyesOfDice) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + eyesOfDice);

        if (currentPlayer.isInPenaltyBox()) {
            playWithPenalty(eyesOfDice);
        } else {
            moveAndAskQuestionFor(eyesOfDice);
        }
    }

    private void playWithPenalty(int eyesOfDice) {
        if (isOdd(eyesOfDice)) {
            currentPlayer.willGetOutOfPenaltyBox();
            moveAndAskQuestionFor(eyesOfDice);
        } else {
            currentPlayer.willStayInPenaltyBox();
        }
    }

    private boolean isOdd(int eyesOfDice) {
        return eyesOfDice % 2 != 0;
    }

    private void moveAndAskQuestionFor(int eyesOfDice) {
        currentPlayer.advanceBy(eyesOfDice);
        askQuestion();
    }

    private void askQuestion() {
        Category currentCategory = currentPlayer.currentCategory();
        System.out.println("The category is " + currentCategory.displayName());
        System.out.println(questions.nextFor(currentCategory));
    }

    public boolean correctAnswer() {
        if (currentPlayer.isInPenaltyBox()) {
            return correctAnswerInPenaltyBox();
        } else {
            return playerWinsCoin(); // System.out.println had typo "corrent" instead of "correct"
        }
        // TODO what is that boolean return value?
    }

    private boolean correctAnswerInPenaltyBox() {
        if (currentPlayer.isGettingOutOfPenaltyBox()) {
            return playerWinsCoin();
        } else {
            return playerDoesNotWinCoin();
        }
    }

    private boolean playerDoesNotWinCoin() {
        currentPlayer.nextPlayer();
        return true;
    }

    private boolean playerWinsCoin() {
        System.out.println("Answer was correct!!!!");
        currentPlayer.answeredCorrect();

        boolean notWinner = currentPlayer.didNotWin();
        currentPlayer.nextPlayer();

        return notWinner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        currentPlayer.goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

    // TODO restructure methods to be either high or low level so we can see the algorithm
    // TODO factor out/separate the output, have it in one place only
}

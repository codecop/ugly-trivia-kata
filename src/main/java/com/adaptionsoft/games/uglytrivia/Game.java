package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Game {

    private final Questions questions;
    private final CurrentPlayer currentPlayer;
    private final GameUi showGame;

    public Game(CurrentPlayer currentPlayer, Questions questions, GameUi showGame) {
        this.currentPlayer = currentPlayer;
        this.questions = questions;
        this.showGame = showGame;
    }

    void takeTurn(int eyesOfDice) {
        currentPlayer.hasRolled(eyesOfDice);
        handlePenalty(eyesOfDice);
        advanceBy(eyesOfDice);
        askQuestion();
    }

    private void handlePenalty(int eyesOfDice) {
        // TODO NEXT - feature envy move into player completely (tests will break)
        if (currentPlayer.isInPenaltyBox()) {
            if (!isOdd(eyesOfDice)) {
                currentPlayer.willStayInPenaltyBox();
            } else {
                currentPlayer.willGetOutOfPenaltyBox();
            }
        }
    }

    private boolean isOdd(int eyesOfDice) {
        return eyesOfDice % 2 != 0;
    }

    private void advanceBy(int eyesOfDice) {
        // TODO NEXT - feature envy move into player completely (tests will break)
        if (currentPlayer.isInPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
            return;
        }

        currentPlayer.advanceBy(eyesOfDice);
    }

    private void askQuestion() {
        if (currentPlayer.isInPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
            return;
        }

        Category currentCategory = currentPlayer.currentCategory();
        showGame.question(currentCategory.displayName(), questions.nextFor(currentCategory));
    }

    boolean correctAnswer() {
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
        showGame.correctAnswer();
        currentPlayer.answeredCorrect();

        boolean notWinner = currentPlayer.didNotWin();
        currentPlayer.nextPlayer();

        return notWinner;
    }

    boolean wrongAnswer() {
        showGame.wrongAnswer();
        currentPlayer.goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

    public void play(Random rand) {
        // TODO (nothing new, skipped) bring under test with stubbed random
        boolean notAWinner;
        do {

            takeTurn(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = wrongAnswer();
            } else {
                notAWinner = correctAnswer();
            }

        } while (notAWinner);
        /*
        TODO NEXT restructure methods to be either high or low level so we can see the algorithm - maybe done?
        make all called methods private
        rename all called methods, e.g. hasRolled is twice
         */
    }

}

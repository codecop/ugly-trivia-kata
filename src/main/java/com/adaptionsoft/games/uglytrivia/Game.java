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

    public void play(int eyesOfDice) {
        currentPlayer.play(eyesOfDice);        

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
        showGame.question(currentCategory.displayName(), questions.nextFor(currentCategory));
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
        showGame.correctAnswer();
        currentPlayer.answeredCorrect();

        boolean notWinner = currentPlayer.didNotWin();
        currentPlayer.nextPlayer();

        return notWinner;
    }

    public boolean wrongAnswer() {
        showGame.wrongAnswer();
        currentPlayer.goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

    public void play(Random rand) {
        boolean notAWinner;
        do {

            play(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = wrongAnswer();
            } else {
                notAWinner = correctAnswer();
            }

        } while (notAWinner);
        /*
        TODO NEXT restructure methods to be either high or low level so we can see the algorithm - maybe done?
        add tests with stubbed random
        make all called methods private
        rename all called methods, e.g. play is twice
         */
    }

}

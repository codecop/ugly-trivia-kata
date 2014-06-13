package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions;
    private final Players currentPlayer;

    // TODO smell: Players class reads better as currentPlayer field.

    static class UI {

        public void currentPlayersTurn(String name, int eyesOfDice) {
            System.out.println(name + " is the current player");
            System.out.println("They have rolled a " + eyesOfDice);
        }

        public void nextQuestion(String category, String question) {
            System.out.println("The category is " + category);
            System.out.println(question);
        }

        public void playerAnsweringCorrect() {
            System.out.println("Answer was correct!!!!");
        }

        public void playerAnsweringIncorrect() {
            System.out.println("Question was incorrectly answered");
        }

    }

    final UI show = new UI();

    public Game(Players currentPlayer, Questions questions) {
        this.currentPlayer = currentPlayer;
        this.questions = questions;
    }

    public void play(int eyesOfDice) {
        show.currentPlayersTurn(currentPlayer.getName(), eyesOfDice);

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
        show.nextQuestion(currentCategory.displayName(), questions.nextFor(currentCategory));
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
        show.playerAnsweringCorrect();
        currentPlayer.answeredCorrect();

        boolean notWinner = currentPlayer.didNotWin();
        currentPlayer.nextPlayer();

        return notWinner;
    }

    public boolean wrongAnswer() {
        show.playerAnsweringIncorrect();
        currentPlayer.goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

    // TODO restructure methods to be either high or low level so we can see the algorithm
    // TODO factor out/separate the output, have it in one place only
}

package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions = new Questions();
    private final Players players = new Players();

    public void add(String playerName) {
        players.add(playerName);
    }

    public void playCurrentPlayer(int eyesOfDice) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + eyesOfDice);

        if (getCurrentPlayer().isInPenaltyBox()) {
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
        String currentCategory = getCurrentPlayer().currentCategory();
        System.out.println("The category is " + currentCategory);
        System.out.println(questions.nextFor(currentCategory));
    }

    private Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    public boolean correctAnswer() {
        if (getCurrentPlayer().isInPenaltyBox()) {
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
        getCurrentPlayer().answeredCorrect();

        boolean notWinner = getCurrentPlayer().didPlayerNotWin();
        players.changeCurrentPlayer();

        return notWinner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        getCurrentPlayer().goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

}

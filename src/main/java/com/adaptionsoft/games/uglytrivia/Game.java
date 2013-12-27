package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
   
    public static final int NUMBER_QUESTIONS = 50;

    private final Players players = new Players();

    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    public Game() {
        createQuestions();
    }

    // TODO move questions, categories and everything into Place. Place knows its category and its questions or the Category knows its question.

    private void createQuestions() {
        for (int i = 0; i < NUMBER_QUESTIONS; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

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
        System.out.println("The category is " + currentCategory());

        if (currentCategory().equals("Pop"))                               // TODO categories? enum?
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        return getCurrentPlayer().currentCategory();
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

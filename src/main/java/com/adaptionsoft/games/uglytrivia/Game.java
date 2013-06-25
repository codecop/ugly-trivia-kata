package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int MAXIMUM_NUMBER_PLAYERS = 6;
    public static final int NUMBER_QUESTIONS = 50;

    private List<Player> players = new ArrayList<Player>();               // players inconsistent with places

    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    private int currentPlayer;
    private boolean isGettingOutOfPenaltyBox; // TODO state is global not for each player? player can never come out of penalty box?

    public Game() {
        createQuestions();
    }

    // TODO move questions, categories and everything into place. place knows its category and its questions or the category know its question.

    private void createQuestions() {
        for (int i = 0; i < NUMBER_QUESTIONS; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void add(String playerName) {
        // no check for 6

        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
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
            isGettingOutOfPenaltyBox = true;
            System.out.println(getCurrentPlayer() + " is getting out of the penalty box");

            moveAndAskCurrentPlayerFor(eyesOfDice);
        } else {
            isGettingOutOfPenaltyBox = false;
            System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
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
        int place = getCurrentPlayer().getPlace();
        return currentCategory(place % 4);
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    private String currentCategory(int place) {
        if (place == 0) return "Pop";
        if (place == 1) return "Science";
        if (place == 2) return "Sports";
        if (place == 3) return "Rock";
        return "Rock"; // TODO throw new IllegalStateException("Current player is out of places");
    }

    public boolean correctAnswer() {
        if (getCurrentPlayer().isInPenaltyBox()) {
            return correctAnswerInPenaltyBox();
        } else {
            return playerWinsCoin();
        }
    }

    private boolean correctAnswerInPenaltyBox() {
        if (isGettingOutOfPenaltyBox) {
            return playerWinsCoin();
        } else {
            return playerDoesNotWinCoin();
        }
    }

    private boolean playerDoesNotWinCoin() {
        changeCurrentPlayer();
        return true;
    }

    private boolean playerWinsCoin() {
        getCurrentPlayer().answeredCorrect();

        boolean notWinner = getCurrentPlayer().didPlayerNotWin();
        changeCurrentPlayer();

        return notWinner;
    }

    private void changeCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        getCurrentPlayer().goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

}

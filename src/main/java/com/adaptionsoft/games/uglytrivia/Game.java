package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int MAXIMUM_NUMBER_PLAYERS = 6;
    public static final int NUMBER_QUESTIONS = 50;
    public static final int NUMBER_PLACES = 12;
    public static final int NEEDED_COINS_TO_WIN = 6;

    private List<String> players = new ArrayList<String>();               // players inconsistent with places
    // TODO have class player with all its meta data inside, no indexing
    private int[] places = new int[MAXIMUM_NUMBER_PLAYERS];                               //   places to sit in 0-11
    private int[] purses = new int[MAXIMUM_NUMBER_PLAYERS];
    private boolean[] inPenaltyBox = new boolean[MAXIMUM_NUMBER_PLAYERS];

    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    private int currentPlayer;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        createQuestions();
    }

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

        players.add(playerName);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void playCurrentPlayer(int eyesOfDice) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + eyesOfDice);

        if (inPenaltyBox[currentPlayer]) {
            handleCurrentPlayerPenalty(eyesOfDice);
        } else {
            moveAndAskCurrentPlayerFor(eyesOfDice);
        }

    }

    private void handleCurrentPlayerPenalty(int eyesOfDice) {
        if (isOdd(eyesOfDice)) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

            moveAndAskCurrentPlayerFor(eyesOfDice);
        } else {
            isGettingOutOfPenaltyBox = false;
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        }
    }

    private boolean isOdd(int eyesOfDice) {
        return eyesOfDice % 2 != 0;
    }

    private void moveAndAskCurrentPlayerFor(int eyesOfDice) {
        advanceCurrentPlayerBy(eyesOfDice);
        askQuestion();
    }

    private void advanceCurrentPlayerBy(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > NUMBER_PLACES -1) places[currentPlayer] = places[currentPlayer] - NUMBER_PLACES;
        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
    }

    private void askQuestion() {
        System.out.println("The category is " + currentCategory());

        if (currentCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0) return "Pop";
        if (places[currentPlayer] == 4) return "Pop";
        if (places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1) return "Science";
        if (places[currentPlayer] == 5) return "Science";
        if (places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2) return "Sports";
        if (places[currentPlayer] == 6) return "Sports";
        if (places[currentPlayer] == 10) return "Sports";
        if (places[currentPlayer] == 3) return "Rock";
        if (places[currentPlayer] == 7) return "Rock";
        if (places[currentPlayer] == 11) return "Rock";

        return "Rock"; // TODO throw new IllegalStateException("Current player is out of places");
    }

    public boolean correctAnswer() {
        if (inPenaltyBox[currentPlayer]) {
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
        playerGetsCoin();

        boolean notWinner = didPlayerNotWin();
        changeCurrentPlayer();

        return notWinner;
    }

    private void changeCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private void playerGetsCoin() {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        goToPenaltyBox();

        return playerDoesNotWinCoin();
    }

    private void goToPenaltyBox() {
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
    }


    private boolean didPlayerNotWin() {
        return !(purses[currentPlayer] == NEEDED_COINS_TO_WIN);
    }
}

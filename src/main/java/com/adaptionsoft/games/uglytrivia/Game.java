package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int MAXIMUM_NUMBER_PLAYERS = 6;
    public static final int NUMBER_QUESTIONS = 50;

    private List<Player> players = new ArrayList<Player>(); 
    private Place[] places = new Place[MAXIMUM_NUMBER_PLAYERS]; 
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

        players.add(new Player(playerName));
        places[players.size()-1] = new Place();
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
        places[currentPlayer].advanceCurrentPlayerBy(eyesOfDice);
        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer].getPlace());
        askQuestion();
    }

    private void askQuestion() {
        System.out.println("The category is " + currentCategory());

        if (currentCategory().equals("Pop")) // TODO categories as enum?
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        int place = places[currentPlayer].getPlace();
        return currentCategory(place % 4);
    }

    private String currentCategory(int place) {
        if (place == 0) return "Pop";
        if (place == 1) return "Science";
        if (place == 2) return "Sports";
        if (place == 3) return "Rock";
        return "Rock"; 
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
        players.get(currentPlayer).answeredCorrect();

        boolean notWinner = players.get(currentPlayer).didPlayerNotWin();
        changeCurrentPlayer();

        return notWinner;
    }

    private void changeCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
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

}

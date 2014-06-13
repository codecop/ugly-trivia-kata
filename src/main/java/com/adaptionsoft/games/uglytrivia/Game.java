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

        // TODO name is first, use PlayerUI?

        public void playersGold(String name, int purse) {
            System.out.println(name + " now has " + purse + " Gold Coins.");
        }

        public void playersLocation(String name, int place) {
            System.out.println(name + "'s new location is " + place);
        }

        public void playerInPenaltyBox(String name) {
            System.out.println(name + " was sent to the penalty box");
        }

        public void playerGettingOutOfBox(String name, boolean gettingOutOfPenaltyBox) {
            String maybe = "not ";
            if (gettingOutOfPenaltyBox) {
                maybe = "";
            }
            System.out.println(name + " is " + maybe + "getting out of the penalty box");
        }

        public void playerHasBeenAdded(String name, int size) {
            System.out.println(name + " was added");
            System.out.println("They are player number " + size);
        }

    }

    final UI show = new UI();

    public Game(Players currentPlayer, Questions questions) {
        this.currentPlayer = currentPlayer;
        this.questions = questions;
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

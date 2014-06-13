package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions;
    private final Players currentPlayer;

    // TODO smell: Players class reads better as currentPlayer field.

    static class UI {
        // TODO 1. move UI out 
        // TODO 2. split UI into GameDisplay and PlayerDisplay (maybe superclass UI with System.out)
        // TODO 3. provide UI from outside using ctors
        // TODO create tests for ui, reuse (copy) test that capture (2 or 3)
        // TODO change test cases that capture to verify interaction

        public void question(String category, String question) {
            System.out.println("The category is " + category);
            System.out.println(question);
        }

        public void correctAnswer() {
            System.out.println("Answer was correct!!!!");
        }

        public void wrongAnswer() {
            System.out.println("Question was incorrectly answered");
        }

        public void playerHasBeenAdded(String name, int size) {
            System.out.println(name + " was added");
            System.out.println("They are player number " + size);
        }
        
        // name is first, use PlayerUI?

        public void beginTurn(String name, int eyesOfDice) {
            System.out.println(name + " is the current player"); 
            System.out.println("They have rolled a " + eyesOfDice);
        }
        
        public void moreMoney(String name, int purse) {
            System.out.println(name + " now has " + purse + " Gold Coins.");
        }

        public void advanceToNewPlace(String name, int place) {
            System.out.println(name + "'s new location is " + place);
        }

        public void goIntoPenaltyBox(String name) {
            System.out.println(name + " was sent to the penalty box");
        }

        public void gettingOutOfPenaltyBox(String name) {
            maybeGettingOutOfPenaltyBox(name, "");
        }
        
        public void notGettingOutOfPenaltyBox(String name) {
            maybeGettingOutOfPenaltyBox(name, "not ");
        }

        private void maybeGettingOutOfPenaltyBox(String name, String maybe) {
            System.out.println(name + " is " + maybe + "getting out of the penalty box");
        }

    }

    final UI showGame = new UI();

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

    // TODO restructure methods to be either high or low level so we can see the algorithm - maybe done?
}

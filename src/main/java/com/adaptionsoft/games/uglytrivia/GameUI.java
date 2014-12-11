package com.adaptionsoft.games.uglytrivia;

public class GameUI {
    // TODO 2. add tests for UI/update capturing tests, reuse (copy) test that capture (2 or 3)
    // TODO 3. change test cases that capture to verify interaction
    // TODO 4. common UI that does the low level UI stuff like print, alert sound etc.

    private void print(String msg) {
        System.out.println(msg);
    }

    public void question(String category, String question) {
        print("The category is " + category);
        print(question);
    }

    public void correctAnswer() {
        print("Answer was correct!!!!");
    }

    public void wrongAnswer() {
        print("Question was incorrectly answered");
    }

}

package com.adaptionsoft.games.uglytrivia;

public class GameUI {
    // TODO 3. provide GameUI from outside using constructors
    // TODO create tests for ui, reuse (copy) test that capture (2 or 3)
    // TODO change test cases that capture to verify interaction

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

    public void playerHasBeenAdded(String name, int size) {
        print(name + " was added");
        print("They are player number " + size);
    }

}

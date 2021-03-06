package com.adaptionsoft.games.uglytrivia;

public class GameUi {
    private final ConsolePrinter consolePrinter;

    public GameUi(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    private void print(String msg) {
        consolePrinter.print(msg);
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

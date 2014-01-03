package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
    public static final int NUMBER_QUESTIONS = 50;

    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    public Questions() {
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

    public String nextFor(Category currentCategory) {
        if (currentCategory == null) {
            throw new IllegalArgumentException("no category");
        }
        switch (currentCategory) {
            case POP:
                return popQuestions.removeFirst();
            case SCIENCE:
                return scienceQuestions.removeFirst();
            case SPORTS:
                return sportsQuestions.removeFirst();
            case ROCK:
                return rockQuestions.removeFirst();
            default:
                throw new IllegalArgumentException("unknown category " + currentCategory);
        }
    }
}

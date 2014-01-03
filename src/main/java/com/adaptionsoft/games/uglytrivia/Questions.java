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

    public String nextFor(Object currentCategory) {
        if (currentCategory.equals(Category.POP))                               // TODO categories? enum?
            return popQuestions.removeFirst();
        if (currentCategory.equals(Category.SCIENCE))
            return scienceQuestions.removeFirst();
        if (currentCategory.equals("Sports"))
            return sportsQuestions.removeFirst();
        if (currentCategory.equals("Rock"))
            return rockQuestions.removeFirst();

        throw new IllegalArgumentException(currentCategory.toString());
    }
}

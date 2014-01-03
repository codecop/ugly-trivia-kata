package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Questions {
    public static final int NUMBER_QUESTIONS = 50;

    private final Map<Category, LinkedList<String>> questionsByCategory = new HashMap<Category, LinkedList<String>>();

    public Questions() {
        createQuestions();
    }

    private void createQuestions() {
        questionsByCategory.put(Category.POP, new LinkedList<String>());
        questionsByCategory.put(Category.SCIENCE, new LinkedList<String>());
        questionsByCategory.put(Category.SPORTS, new LinkedList<String>());
        questionsByCategory.put(Category.ROCK, new LinkedList<String>());

        for (int i = 0; i < NUMBER_QUESTIONS; i++) {
            questionsByCategory.get(Category.POP).addLast(Category.POP.displayName() + " Question " + i);
            questionsByCategory.get(Category.SCIENCE).addLast(Category.SCIENCE.displayName() + " Question " + i);
            questionsByCategory.get(Category.SPORTS).addLast(Category.SPORTS.displayName() + " Question " + i);
            questionsByCategory.get(Category.ROCK).addLast(Category.ROCK.displayName() + " Question " + i);
        }
    }

    public String nextFor(Category currentCategory) {
        if (currentCategory == null) {
            throw new IllegalArgumentException("no category");
        }
        return questionsByCategory.get(currentCategory).removeFirst();
    }
}

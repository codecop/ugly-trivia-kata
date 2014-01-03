package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Questions {
    public static final int NUMBER_QUESTIONS = 50;

    private final Map<Category, LinkedList<String>> questionsByCategory = new HashMap<Category, LinkedList<String>>();

    public Questions() {
        create();
    }

    private void create() {
        for (Category category : Category.values()) {
            putFor(category);
        }
    }

    private void putFor(Category category) {
        questionsByCategory.put(category, createFor(category));
    }

    private LinkedList<String> createFor(Category category) {
        LinkedList<String> questions = new LinkedList<String>();
        for (int i = 0; i < NUMBER_QUESTIONS; i++) {
            questions.addLast(category.displayName() + " Question " + i);
        }
        return questions;
    }

    public String nextFor(Category currentCategory) {
        if (currentCategory == null) {
            throw new IllegalArgumentException("no category");
        }
        return questionsByCategory.get(currentCategory).removeFirst();
    }
}

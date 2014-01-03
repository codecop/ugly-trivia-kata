package com.adaptionsoft.games.uglytrivia;

public enum Category {

    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    public static final Category[] ORDERED = {POP, SCIENCE, SPORTS, ROCK};

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}

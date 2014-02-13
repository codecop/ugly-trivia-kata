package com.adaptionsoft.games.uglytrivia;

public class Place {
    private static final int NUMBER_PLACES = 12;

    private int place;

    public void advanceCurrentPlayerBy(int roll) {
        place = place + roll;
        if (place > NUMBER_PLACES - 1) {
            place = place - NUMBER_PLACES;
        }
    }

    int getPlace() {
        return place;
    }

    public Category currentCategory() {
        return Category.ORDERED[place % 4];
    }
}

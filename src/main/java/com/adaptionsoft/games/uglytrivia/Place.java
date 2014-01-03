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

    public Object currentCategory() {
        if (place % 4 == 0) return Category.POP;
        if (place % 4 == 1) return Category.SCIENCE;
        if (place % 4 == 2) return "Sports";
        if (place % 4 == 3) return "Rock";
        return "Rock"; // TODO throw new IllegalStateException("Current player is out of places");
    }
}

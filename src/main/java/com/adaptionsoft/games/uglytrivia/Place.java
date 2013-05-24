package com.adaptionsoft.games.uglytrivia;

public class Place {
    private static final int NUMBER_PLACES = 12;

    private int place;

    void advanceCurrentPlayerBy(int roll) {
        place = place + roll;
        if (place > NUMBER_PLACES -1) place = place - NUMBER_PLACES;
    }

    public int getPlace() {
        return place;
    }

}

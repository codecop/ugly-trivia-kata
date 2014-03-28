package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaceTest {

    private Place place = new Place();

    @Test
    public void shouldAdvance() {
        place.advanceCurrentPlayerBy(3);

        assertEquals(3, place.getPlace());
    }

    @Test
    public void shouldAdvanceBelowBoundary() {
        place.advanceCurrentPlayerBy(5);

        place.advanceCurrentPlayerBy(6);

        assertEquals(11, place.getPlace());
    }

    @Test
    public void shouldAdvanceRoundBoundary() {
        place.advanceCurrentPlayerBy(11);

        place.advanceCurrentPlayerBy(3);

        assertEquals(2, place.getPlace());
    }

}

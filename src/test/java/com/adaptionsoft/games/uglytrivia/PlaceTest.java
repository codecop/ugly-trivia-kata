package com.adaptionsoft.games.uglytrivia;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
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

    @Test
    @Parameters(method = "placesWithCategories")
    public void shouldKnowItsCategories(int advance, String category) {
        place.advanceCurrentPlayerBy(advance);

        assertThat(place.currentCategory(), is(category));
    }

    @SuppressWarnings("unused")
    private Object[] placesWithCategories() {
        return $($(0, "Pop"),
                $(1, "Science"),
                $(2, "Sports"),
                $(3, "Rock"),
                // it should wrap at 4
                $(4, "Pop"),
                $(5, "Science"),
                $(6, "Sports"),
                $(7, "Rock"),
                // it has a total of 12
                $(8, "Pop"),
                $(9, "Science"),
                $(10, "Sports"),
                $(11, "Rock"));
    }
}

package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ReplayingRandomTest {

    private Random rand;

    @Before
    public void createRandom() {
        rand = new ReplayingRandom("2,3,3,4,", "3,3,8,2,");
    }

    @Test
    public void shouldReturnExpectedRandomsFor5And9() {
        assertThat(rand.nextInt(5), equalTo(2));
        assertThat(rand.nextInt(9), equalTo(3));

        assertThat(rand.nextInt(5), equalTo(3));
        assertThat(rand.nextInt(9), equalTo(3));

        assertThat(rand.nextInt(5), equalTo(3));
        assertThat(rand.nextInt(9), equalTo(8));

        assertThat(rand.nextInt(5), equalTo(4));
        assertThat(rand.nextInt(9), equalTo(2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldFailForOthers() {
        rand.nextInt(3);
    }
}

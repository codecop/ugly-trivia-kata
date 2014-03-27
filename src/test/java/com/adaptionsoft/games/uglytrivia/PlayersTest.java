package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PlayersTest {

    public static final String PLAYER_ONE = "Thomas";
    public static final String PLAYER_TWO = "Peter";
    public static final String ANY_PLAYER = "Erik";

    private Players players = new Players();

    @Before
    public void addPlayers() {
        players.add(PLAYER_ONE);
        players.add(PLAYER_TWO);
    }

    @Test
    public void shouldAddOnePlayer() {
        players.add(new Player(ANY_PLAYER));
        players.changeCurrentPlayer();
        players.changeCurrentPlayer();

        assertThat(players.getCurrentPlayerName(), is(ANY_PLAYER));
    }

    @Test
    public void shouldGiveFirstPlayer() {
        assertThat(players.getCurrentPlayerName(), is(PLAYER_ONE));
    }

    @Test
    public void shouldIteratePlayers() {
        players.changeCurrentPlayer();

        assertThat(players.getCurrentPlayerName(), is(PLAYER_TWO));
    }

    @Test
    public void shouldStartWithFirstPlayerAgain() {
        players.changeCurrentPlayer();
        players.changeCurrentPlayer();

        assertThat(players.getCurrentPlayerName(), is(PLAYER_ONE));
    }

    @Test(expected = IllegalAccessException.class)
    @Ignore("not implemented")
    public void shouldFailForMoreThanSixPlayers() {
        players.add(ANY_PLAYER);
        players.add(ANY_PLAYER);
        players.add(ANY_PLAYER);
        players.add(ANY_PLAYER);
        players.add("one too much");
    }
}

package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CurrentPlayerTest {

    public static final String PLAYER_ONE = "Thomas";
    public static final String PLAYER_TWO = "Peter";

    private CurrentPlayer currentPlayer;

    @Before
    public void addPlayers() {
        Players players = new Players();
        players.add(PLAYER_ONE);
        players.add(PLAYER_TWO);
        currentPlayer = new CurrentPlayer(players);
    }

    @Test
    public void shouldGiveFirstPlayer() {
        assertThat(currentPlayer.getName(), is(PLAYER_ONE));
    }

    @Test
    public void shouldIteratePlayers() {
        currentPlayer.nextPlayer();

        assertThat(currentPlayer.getName(), is(PLAYER_TWO));
    }

    @Test
    public void shouldStartWithFirstPlayerAgain() {
        currentPlayer.nextPlayer();
        currentPlayer.nextPlayer();

        assertThat(currentPlayer.getName(), is(PLAYER_ONE));
    }

}
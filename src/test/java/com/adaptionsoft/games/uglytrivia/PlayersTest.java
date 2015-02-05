package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class PlayersTest {

    private static final String PLAYER_ONE = "Thomas";
    private static final String PLAYER_TWO = "Peter";
    private static final String ANY_PLAYER = "Erik";

    // TODO (nothing new, skipped) minor duplication in all Player tests for creating the Players instance.
    private PlayerUi ui = mock(PlayerUi.class);
    private Players players = new Players(ui);

    @Before
    public void addPlayers() {
        players.add(PLAYER_ONE);
        players.add(PLAYER_TWO);
    }

    @Test
    public void shouldAddOnePlayer() {
        assertThat(players.size(), is(2));

        players.add(PlayerBuilder.named(ANY_PLAYER));

        assertThat(players.size(), is(3));
    }

    @Test(expected = IllegalAccessException.class)
    @Ignore("not implemented")
    public void shouldFailForMoreThanSixPlayers() {
        players.add(ANY_PLAYER);
        players.add(ANY_PLAYER);
        players.add(ANY_PLAYER);
        players.add(ANY_PLAYER);
        players.add("one too many");
    }
}

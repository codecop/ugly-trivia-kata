package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    private Player player = new Player("Erik");

    @Test
    public void newPlayerShouldNotWin() {
        assertTrue("new player won", player.didPlayerNotWin());
    }

    @Test
    public void newPlayerWithTooFewCoinsShouldNotWin() {
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();

        assertTrue("new player won", player.didPlayerNotWin());
    }

    @Test
    public void newPlayerWith6CoinsShouldWin() {
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();
        player.answeredCorrect();

        assertFalse("new player did not won", player.didPlayerNotWin());
    }
}

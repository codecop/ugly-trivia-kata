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
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();

        assertTrue("new player won", player.didPlayerNotWin());
    }

    @Test
    public void newPlayerWith6CoinsShouldNotWin() {
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();
        player.playerGetsCoin();

        assertFalse("new player did not won", player.didPlayerNotWin());
    }
}

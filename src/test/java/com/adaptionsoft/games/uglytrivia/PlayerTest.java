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
        answerCorrect(5);

        assertTrue("new player won", player.didPlayerNotWin());
    }

    private void answerCorrect(int times) {
        for (int i = 0; i < times; i++) {
            player.answeredCorrect();
        }
    }

    @Test
    public void newPlayerWith6CoinsShouldNotWin() {
        answerCorrect(6);
 
        assertFalse("new player did not won", player.didPlayerNotWin());
    }
}

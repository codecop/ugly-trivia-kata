package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private Player player = TestPlayer.named("Erik");

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
    public void newPlayerWith6CoinsShouldWin() {
        answerCorrect(6);

        assertFalse("new player did not won", player.didPlayerNotWin());
    }

    @Test
    public void shouldPrintACorrectAnswerMessageWhenScoring() {
        player = new Player("Erik", new PlayerUI());
        // TODO 3. (part 2) change from using the real UI to using the stubbedUI, mock in this case
        new SystemOutCapture() {
            {

                player.answeredCorrect();

                Assert.assertThat(capturedSystemOut(), is("Erik now has 1 Gold Coins." + cr()));

            }
        }.resetSystemOut();
    }

    @Test
    @Ignore("known defect, not implemented")
    public void shouldNotBeInPenaltyBoxAfterExit() {
        player.goToPenaltyBox();
        assertThat(player.isInPenaltyBox(), is(true));

        player.exitPenaltyBox();

        assertThat(player.isInPenaltyBox(), is(false));
    }
}

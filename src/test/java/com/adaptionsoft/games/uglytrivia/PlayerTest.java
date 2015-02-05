package com.adaptionsoft.games.uglytrivia;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class PlayerTest {
    private Player player = PlayerBuilder.named("Erik");

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
    public void shouldVerifyCorrectAnswerMessageWhenScoring() {
        player.answeredCorrect();

        PlayerUi ui = PlayerBuilder.mockedUi();
        String name = PlayerBuilder.name();
        int purseAfterOneCorrectAnswer = 1;
        verify(ui).moreMoney(name, purseAfterOneCorrectAnswer);
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

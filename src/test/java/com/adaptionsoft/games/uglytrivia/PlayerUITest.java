package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerUITest {

    @Test
    public void shouldPrintACorrectAnswerMessageWhenScoring() {
        final PlayerUI playerUi = new PlayerUI();
        new SystemOutCapture() {
            {

                playerUi.moreMoney("Erik", 2);

                assertThat(capturedSystemOut(), is("Erik now has 2 Gold Coins." + cr()));

            }
        }.resetSystemOut();
    }
}

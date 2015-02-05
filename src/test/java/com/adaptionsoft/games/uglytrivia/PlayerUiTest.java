package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerUiTest {

    @Test
    public void shouldPrintACorrectAnswerMessageWhenScoring() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        final PlayerUi playerUi = new PlayerUi(consolePrinter);
        new SystemOutCapture() {
            {

                playerUi.moreMoney("Erik", 2);

                assertThat(capturedSystemOut(), is("Erik now has 2 Gold Coins." + cr()));

            }
        }.resetSystemOut();
    }
}

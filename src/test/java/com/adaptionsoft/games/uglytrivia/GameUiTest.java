package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class GameUiTest {

    @Test
    public void correctAnswerShouldPrintAWinMessage() {
        new SystemOutCapture() {
            {

                GameUi gameUi = new GameUi();

                gameUi.correctAnswer();

                Assert.assertThat(capturedSystemOut(), is("Answer was correct!!!!" + cr()));

            }
        }.resetSystemOut();
    }

}

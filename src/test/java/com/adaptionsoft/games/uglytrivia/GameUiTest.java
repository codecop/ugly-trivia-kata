package com.adaptionsoft.games.uglytrivia;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @Test
    public void wrongAnswerShouldPrintAWrongAnswerMessage() {
        new SystemOutCapture() {
            {

                GameUi gameUi = new GameUi();

                gameUi.wrongAnswer();

                assertThat(capturedSystemOut(), Is.is("Question was incorrectly answered" + cr()));

            }
        }.resetSystemOut();
    }
}

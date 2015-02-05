package com.adaptionsoft.games.uglytrivia;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameUiTest {
    ConsolePrinter consolePrinter = new ConsolePrinter();
    private GameUi gameUi = new GameUi(consolePrinter);

    @Test
    public void correctAnswerShouldPrintAWinMessage() {
        new SystemOutCapture() {
            {

                gameUi.correctAnswer();

                Assert.assertThat(capturedSystemOut(), is("Answer was correct!!!!" + cr()));

            }
        }.resetSystemOut();
    }

    @Test
    public void wrongAnswerShouldPrintAWrongAnswerMessage() {
        new SystemOutCapture() {
            {

                gameUi.wrongAnswer();

                assertThat(capturedSystemOut(), Is.is("Question was incorrectly answered" + cr()));

            }
        }.resetSystemOut();
    }


    @Test
    public void shouldPrintQuestion() {
        new SystemOutCapture() {
            {

                gameUi.question("anything", "Pop Question 2");

                assertThat(capturedSystemOutLines(), hasItems("Pop Question 2"));

            }
        }.resetSystemOut();
    }

}

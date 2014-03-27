package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameWrongAnswerTest {

    @Test
    public void wrongAnswerShouldSendPlayerToPenaltyBox() {
        Player lonelyTom = new Player("Lonely Tom");
        Game game = GameBuilder.createGameWithSinglePlayer(lonelyTom);

        game.wrongAnswer();

        assertTrue("Player should be in penalty box", lonelyTom.isInPenaltyBox());
    }

    @Test
    public void wrongAnswerShouldNotGetACoin() {
        Player player = mock(Player.class);
        Game game = GameBuilder.createGameWithSinglePlayer(player);

        game.wrongAnswer();

        verify(player, never()).answeredCorrect();
    }

    @Test
    public void wrongAnswerShouldEndTurn() {
        Players players = mock(Players.class);
        Game game = GameBuilder.createGameWith(players);

        game.wrongAnswer();

        verify(players).nextPlayer();
    }

    @Test
    public void wrongAnswerShouldPrintAWrongAnswerMessage() {
        new SystemOutCapture() {
            {

                Players players = mock(Players.class);
                Game game = GameBuilder.createGameWith(players);

                game.wrongAnswer();

                assertThat(capturedSystemOut(), is("Question was incorrectly answered" + cr()));

            }
        }.resetSystemOut();
    }

    @Test
    public void wrongAnswerShouldNeverWinGame() {
        Players players = mock(Players.class);
        Game game = GameBuilder.createGameWith(players);

        assertFalse("Wrong answer should never win game", !game.wrongAnswer());
    }
}

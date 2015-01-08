package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GameWrongAnswerTest {

    @Test
    public void wrongAnswerShouldSendPlayerToPenaltyBox() {
        Player lonelyTom = TestPlayer.named("Lonely Tom");
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
        CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
        Game game = GameBuilder.createGameWith(currentPlayer);

        game.wrongAnswer();

        verify(currentPlayer).nextPlayer();
    }

    @Test
    public void wrongAnswerShouldNotifyWrongAnswer() {
        CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
        Game game = GameBuilder.createMockedGameWith(currentPlayer);

        game.wrongAnswer();

        verify(GameBuilder.mockedUi()).wrongAnswer();
    }

    @Test
    public void wrongAnswerShouldNeverWinGame() {
        CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
        Game game = GameBuilder.createGameWith(currentPlayer);

        assertFalse("Wrong answer should never win game", !game.wrongAnswer());
    }
}

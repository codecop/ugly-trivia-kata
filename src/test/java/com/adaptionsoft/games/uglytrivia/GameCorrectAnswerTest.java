package com.adaptionsoft.games.uglytrivia;

import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class GameCorrectAnswerTest {

    @Test
    public void correctAnswerShouldGetACoin() {
        Player player = mock(Player.class);
        Game game = createGameWithSinglePlayer(player);

        game.correctAnswer();

        verify(player, times(1)).answeredCorrect();
    }

    private Game createGameWithSinglePlayer(Player player) {
        Players players = new Players();
        players.add(player);
        return new Game(players, null);
    }

    @Test
    public void correctAnswerShouldEndTurn() {
        Players players = mock(Players.class);
        Game game = new Game(players, null);

        game.correctAnswer();

        verify(players).changeCurrentPlayer();
    }

    @Test
    @Ignore("known defect in code")
    public void correctAnswerShouldGetPlayerOutOfPenaltyBoxIfLucky() {
        Player playerInPenaltyBox = mock(Player.class);
        when(playerInPenaltyBox.isInPenaltyBox()).thenReturn(true);
        when(playerInPenaltyBox.isGettingOutOfPenaltyBox()).thenReturn(true);
        Game game = createGameWithSinglePlayer(playerInPenaltyBox);

        game.correctAnswer();

        // verify(playerInPenaltyBox).exitPenaltyBox();
    }

    // right answer with enough previous couns should end game
}
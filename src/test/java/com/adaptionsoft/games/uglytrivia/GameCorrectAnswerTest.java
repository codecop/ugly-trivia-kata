package com.adaptionsoft.games.uglytrivia;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public void correctAnswerShouldGetLuckyPlayerOutOfPenaltyBox() {
        Player playerInPenaltyBox = mock(Player.class);
        when(playerInPenaltyBox.isInPenaltyBox()).thenReturn(true);
        final boolean lucky = true;
        when(playerInPenaltyBox.isGettingOutOfPenaltyBox()).thenReturn(lucky);
        Game game = createGameWithSinglePlayer(playerInPenaltyBox);

        game.correctAnswer();

        verify(playerInPenaltyBox).exitPenaltyBox();
    }

    @Test
    public void correctAnswerShouldNotGetUnluckyPlayerOutOfPenaltyBox() {
        Player playerInPenaltyBox = mock(Player.class);
        when(playerInPenaltyBox.isInPenaltyBox()).thenReturn(true);
        final boolean unlucky = false;
        when(playerInPenaltyBox.isGettingOutOfPenaltyBox()).thenReturn(unlucky);
        Game game = createGameWithSinglePlayer(playerInPenaltyBox);

        game.correctAnswer();

        verify(playerInPenaltyBox, never()).exitPenaltyBox();
    }

    @Test
    public void correctAnswerWithNotEnoughCoinsDoesNotEndGame() {
        Player player = new Player("unlucky");
        Game game = createGameWithSinglePlayer(player);

        for (int i = 0; i < 5; i++) {
            assertThat(game.correctAnswer(), is(true));
        }
    }

    @Test
    @Ignore
    public void correctAnswerWithEnoughCoinsEndsGame() {
        Player player = mock(Player.class);
        Game game = createGameWithSinglePlayer(player);

        game.correctAnswer();


    }

    // TODO add test for output if any
    // TODO refactor duplicated createGameWithSinglePlayer in both test classes
    // TODO verify this are all test cases for correct answer
}
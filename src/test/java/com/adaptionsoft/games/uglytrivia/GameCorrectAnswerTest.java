package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class GameCorrectAnswerTest {

    @Test
    public void correctAnswerShouldGetACoin() {
        Player player = mock(Player.class);
        Game game = GameBuilder.createGameWithSinglePlayer(player);

        game.correctAnswer();

        verify(player, times(1)).answeredCorrect();
    }

    @Test
    public void correctAnswerShouldEndTurn() {
        CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
        Game game = GameBuilder.createGameWith(currentPlayer);

        game.correctAnswer();

        verify(currentPlayer).nextPlayer();
    }

    @Test
    @Ignore("known defect in code")
    public void correctAnswerShouldGetLuckyPlayerOutOfPenaltyBox() {
        Player playerInPenaltyBox = mock(Player.class);
        when(playerInPenaltyBox.isInPenaltyBox()).thenReturn(true);
        final boolean lucky = true;
        when(playerInPenaltyBox.isGettingOutOfPenaltyBox()).thenReturn(lucky);
        Game game = GameBuilder.createGameWithSinglePlayer(playerInPenaltyBox);

        game.correctAnswer();

        verify(playerInPenaltyBox).exitPenaltyBox();
    }

    @Test
    public void correctAnswerShouldNotGetUnluckyPlayerOutOfPenaltyBox() {
        Player playerInPenaltyBox = mock(Player.class);
        when(playerInPenaltyBox.isInPenaltyBox()).thenReturn(true);
        final boolean unlucky = false;
        when(playerInPenaltyBox.isGettingOutOfPenaltyBox()).thenReturn(unlucky);
        Game game = GameBuilder.createGameWithSinglePlayer(playerInPenaltyBox);

        game.correctAnswer();

        verify(playerInPenaltyBox, never()).exitPenaltyBox();
    }

    @Test
    public void correctAnswerWithNotEnoughCoinsDoesNotEndGame() {
        Player player = TestPlayer.named("Unlucky Bill");
        Game game = GameBuilder.createGameWithSinglePlayer(player);

        for (int i = 0; i < 5; i++) {
            assertThat(game.correctAnswer(), is(true));
        }
    }

    @Test
    public void correctAnswerWithEnoughCoinsEndsGame() {
        Player player = TestPlayer.named("Lucky Luke");
        Game game = GameBuilder.createGameWithSinglePlayer(player);
        for (int i = 0; i < 5; i++) {
            game.correctAnswer();
        }

        assertThat(game.correctAnswer(), is(false));
    }

    @Test
    public void correctAnswerShouldPrintAWinMessage() {
        new SystemOutCapture() {
            {

                CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
                Game game = GameBuilder.createGameWith(currentPlayer);

                game.correctAnswer();

                Assert.assertThat(capturedSystemOut(), is("Answer was correct!!!!" + cr()));

            }
        }.resetSystemOut();
    }

}
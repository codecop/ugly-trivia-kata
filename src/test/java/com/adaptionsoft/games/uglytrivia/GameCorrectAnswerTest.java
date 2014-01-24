package com.adaptionsoft.games.uglytrivia;

import org.junit.Ignore;
import org.junit.Test;

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
}
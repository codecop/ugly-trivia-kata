package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GamePlayTest {

    @Test
    public void playerShouldAdvanceByEyesOfDice() {
        int eyesOfDice = 3;

        Players players = mock(Players.class);
        Questions questions = mock(Questions.class);
        Game game = GameBuilder.createGameWith(players, questions);

        game.playCurrentPlayer(eyesOfDice);

        verify(players).advanceBy(eyesOfDice);
    }

}

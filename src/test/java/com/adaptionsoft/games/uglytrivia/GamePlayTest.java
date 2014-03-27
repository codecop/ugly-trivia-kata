package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GamePlayTest {

    private Players players = mock(Players.class);
    private Questions questions = mock(Questions.class);
    private Game game = GameBuilder.createGameWith(players, questions);

    @Before
    public void prepareQuestion() {
        when(players.currentCategory()).thenReturn(Category.SPORTS);
    }
    
    @Test
    public void playerShouldAdvanceByEyesOfDice() {
        int eyesOfDice = 3;

        game.playCurrentPlayer(eyesOfDice);

        verify(players).advanceBy(eyesOfDice);
    }

    @Test
    public void playerShouldBeAskedQuestion() {
        new SystemOutCapture() {
            {
                when(questions.nextFor(any(Category.class))).thenReturn("Pop Question 1");
                int anyEye = 0;

                game.playCurrentPlayer(anyEye);

                assertThat(capturedSystemOutLines(), hasItems("Pop Question 1"));
            }
        }.resetSystemOut();
    }

    @Test
    public void playerShouldBeAskedQuestionBelongingToCategoryOfPlace() {
        when(players.currentCategory()).thenReturn(Category.POP);
        int anyEye = 7;

        game.playCurrentPlayer(anyEye);

        verify(questions).nextFor(Category.POP);
    }
}

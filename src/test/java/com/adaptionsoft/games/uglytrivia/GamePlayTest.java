package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GamePlayTest {

    private CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
    private Questions questions = mock(Questions.class);
    private Game game = GameBuilder.createGameWith(currentPlayer, questions);

    @Before
    public void prepareQuestion() {
        when(currentPlayer.currentCategory()).thenReturn(Category.SPORTS);
    }
    
    @Test
    public void playerShouldAdvanceByEyesOfDice() {
        int eyesOfDice = 3;

        game.play(eyesOfDice);

        verify(currentPlayer).advanceBy(eyesOfDice);
    }

    @Test
    public void playerShouldBeAskedQuestion() {
        new SystemOutCapture() {
            {
                when(questions.nextFor(any(Category.class))).thenReturn("Pop Question 1");
                int anyEye = 0;

                game.play(anyEye);

                assertThat(capturedSystemOutLines(), hasItems("Pop Question 1"));
            }
        }.resetSystemOut();
    }

    @Test
    public void playerShouldBeAskedQuestionBelongingToCategoryOfPlace() {
        when(currentPlayer.currentCategory()).thenReturn(Category.POP);
        int anyEye = 7;

        game.play(anyEye);

        verify(questions).nextFor(Category.POP);
    }
}

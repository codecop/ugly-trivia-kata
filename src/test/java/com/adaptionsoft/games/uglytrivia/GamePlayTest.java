package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GamePlayTest {

    private CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
    private Questions questions = mock(Questions.class);
    private Game game = GameBuilder.createMockedGameWith(currentPlayer, questions);

    @Before
    public void prepareQuestion() {
        when(currentPlayer.currentCategory()).thenReturn(Category.SPORTS);
    }

    @Test
    public void playerShouldAdvanceByEyesOfDice() {
        int eyesOfDice = 3;

        game.takeTurn(eyesOfDice);

        verify(currentPlayer).advanceBy(eyesOfDice);
    }

    @Test
    public void playerShouldBeAskedQuestion() {
        Category anyCategory = Category.SPORTS;
        String nextQuestion = "Sport Question 1";
        when(currentPlayer.currentCategory()).thenReturn(anyCategory);
        when(questions.nextFor(any(Category.class))).thenReturn(nextQuestion);
        int anyEye = 0;

        game.takeTurn(anyEye);

        verify(GameBuilder.mockedUi()).question(anyCategory.displayName(), nextQuestion);
    }

    @Test
    public void playerShouldBeAskedQuestionBelongingToCategoryOfPlace() {
        when(currentPlayer.currentCategory()).thenReturn(Category.POP);
        int anyEye = 7;

        game.takeTurn(anyEye);

        verify(questions).nextFor(Category.POP);
    }
}

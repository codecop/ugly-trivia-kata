package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GamePenaltyTest {
    private CurrentPlayer currentPlayer = mock(CurrentPlayer.class);
    private Questions questions = mock(Questions.class);
    private Game game = GameBuilder.createGameWith(currentPlayer, questions);

    @Before
    public void prepareQuestion() {
        when(currentPlayer.currentCategory()).thenReturn(Category.SPORTS);
    }

    @Before
    public void putPlayerIntoPenaltyBox() {
        when(currentPlayer.isInPenaltyBox()).thenReturn(true);
    }

    @Test
    public void playerInPenaltyBoxShouldGetOutOnOddDice() {
        int oddEyesOfDice = 1;

        game.takeTurn(oddEyesOfDice);

        verify(currentPlayer).willGetOutOfPenaltyBox();
    }

    @Test
    public void playerInPenaltyBoxShouldAdvanceByOddEyesOfDice() {
        int oddEyesOfDice = 1;

        game.takeTurn(oddEyesOfDice);

        verify(currentPlayer).advanceBy(oddEyesOfDice);
    }

    @Test
    // TODO (nothing new, skipped) add a direct capture test to Ui test and create interaction test here
    public void playerInPenaltyBoxShouldBeAskedQuestionOnOddDice() {
        new SystemOutCapture() {
            {
                when(questions.nextFor(any(Category.class))).thenReturn("Pop Question 1");
                int oddEyesOfDice = 1;

                game.takeTurn(oddEyesOfDice);

                assertThat(capturedSystemOutLines(), hasItems("Pop Question 1"));
            }
        }.resetSystemOut();
    }

    @Test
    public void playerInPenaltyBoxShouldNotGetOutOnEvenDice() {
        int evenEyesOfDice = 2;

        game.takeTurn(evenEyesOfDice);

        verify(currentPlayer).willStayInPenaltyBox();
    }

    @Test
    public void playerInPenaltyBoxShouldNeverAdvanceOnEvenEyesOfDice() {
        int evenEyesOfDice = 2;

        game.takeTurn(evenEyesOfDice);

        verify(currentPlayer, never()).advanceBy(any(Integer.class));
    }

    @Test
    public void playerInPenaltyBoxShouldWinCoinOnCorrectAnswer() {
        when(currentPlayer.isGettingOutOfPenaltyBox()).thenReturn(true);

        game.correctAnswer();

        verify(currentPlayer, times(1)).answeredCorrect();
    }
}

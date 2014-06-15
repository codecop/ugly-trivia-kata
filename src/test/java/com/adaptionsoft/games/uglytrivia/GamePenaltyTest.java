package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class GamePenaltyTest {
    private Players players = mock(Players.class);
    private Questions questions = mock(Questions.class);
    private Game game = GameBuilder.createGameWith(players, questions);

    @Before
    public void prepareQuestion() {
        when(players.currentCategory()).thenReturn(Category.SPORTS);
    }

    @Before
    public void putPlayerIntoPenaltyBox() {
        when(players.isInPenaltyBox()).thenReturn(true);
    }

    @Test
    public void playerInPenaltyBoxShouldGetOutOnOddDice() {
        int oddEyesOfDice = 1;

        game.play(oddEyesOfDice);

        verify(players).willGetOutOfPenaltyBox();
    }

    @Test
    public void playerInPenaltyBoxShouldAdvanceByOddEyesOfDice() {
        int oddEyesOfDice = 1;

        game.play(oddEyesOfDice);

        verify(players).advanceBy(oddEyesOfDice);
    }

    @Test
    public void playerInPenaltyBoxShouldBeAskedQuestionOnOddDice() {
        new SystemOutCapture() {
            {
                when(questions.nextFor(any(Category.class))).thenReturn("Pop Question 1");
                int oddEyesOfDice = 1;

                game.play(oddEyesOfDice);

                assertThat(capturedSystemOutLines(), hasItems("Pop Question 1"));
            }
        }.resetSystemOut();
    }

    @Test
    public void playerInPenaltyBoxShouldNotGetOutOnEvenDice() {
        int evenEyesOfDice = 2;

        game.play(evenEyesOfDice);

        verify(players).willStayInPenaltyBox();
    }

    @Test
    public void playerInPenaltyBoxShouldNeverAdvanceOnEvenEyesOfDice() {
        int evenEyesOfDice = 2;

        game.play(evenEyesOfDice);

        verify(players, never()).advanceBy(any(Integer.class));
    }

    @Test
    public void playerInPenaltyBoxShouldWinCoinOnCorrectAnswer(){
        when(players.isGettingOutOfPenaltyBox()).thenReturn(true);

        game.correctAnswer();

        verify(players, times(1)).answeredCorrect();
    }
}

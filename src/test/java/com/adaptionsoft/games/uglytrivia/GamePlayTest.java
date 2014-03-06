package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void playerShouldBeAskedQuestion() {
        new SystemOutCapture() {
            {

                Players players = mock(Players.class);
                Questions questions = mock(Questions.class);
                when(questions.nextFor(any(Category.class))).thenReturn("Pop Question 1");
                Game game = GameBuilder.createGameWith(players, questions);

                int anyEye = 0;
                game.playCurrentPlayer(anyEye);

                assertThat(capturedSystemOutLines(), hasItems("Pop Question 1" ));

            }

            private List<String> capturedSystemOutLines() {
                return Arrays.asList(capturedSystemOut().split(cr()));
            }
        }.resetSystemOut();
    }

    @Test
    public void playerShouldBeAskedQuestionBelongingToCategoryOfPlace() {
        Players players = mock(Players.class);
        when(players.currentCategory()).thenReturn(Category.POP);
        Questions questions = mock(Questions.class);
        Game game = GameBuilder.createGameWith(players, questions);

        int anyEye = 7;
        game.playCurrentPlayer(anyEye);

        verify(questions).nextFor(Category.POP);
    }

}

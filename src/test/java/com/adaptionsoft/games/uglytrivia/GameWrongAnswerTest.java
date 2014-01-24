package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameWrongAnswerTest {

    private PrintStream sysOut;

    @Test
    public void wrongAnswerShouldSendPlayerToPenaltyBox() {
        Player lonelyTom = new Player("Lonely Tom");
        Game game = createGameWithSinglePlayer(lonelyTom);

        game.wrongAnswer();

        assertTrue("Player should be in penalty box", lonelyTom.isInPenaltyBox());
    }

    @Test
    public void wrongAnswerShouldNotGetACoin() {
        Player player = mock(Player.class);
        Game game = createGameWithSinglePlayer(player);

        game.wrongAnswer();

        verify(player, never()).answeredCorrect();
    }

    private Game createGameWithSinglePlayer(Player player) {
        Players players = new Players();
        players.add(player);
        return new Game(players, null);
    }

    @Test
    public void wrongAnswerShouldEndTurn() {
        Players players = mock(Players.class);
        Game game = new Game(players, null);

        game.wrongAnswer();

        verify(players).changeCurrentPlayer();
    }

    @Test
    public void wrongAnswerShouldPrintAWrongAnswerMessage() {
        try {
            ByteArrayOutputStream systemOut = captureSystemOut();

            Players players = mock(Players.class);
            Game game = new Game(players, null);

            game.wrongAnswer();

            assertThat(new String(systemOut.toByteArray()), is("Question was incorrectly answered\n"));
        } finally {
            resetSystemOut();
        }
    }

    private ByteArrayOutputStream captureSystemOut() {
        sysOut = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    private void resetSystemOut() {
        System.setOut(sysOut);
    }

    @Test
    public void wrongAnswerShouldNeverWinGame() {
        Players players = mock(Players.class);
        Game game = new Game(players, null);

        assertFalse("Wrong answer should never win game", !game.wrongAnswer());
    }
}

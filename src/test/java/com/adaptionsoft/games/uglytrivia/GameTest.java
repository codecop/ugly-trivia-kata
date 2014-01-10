package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameTest {
    @Test
    public void wrongAnswerShouldSendPlayerToPenaltyBox() {
        Players players = new Players();
        players.add("Lonely Tom");
        Player player = players.getCurrentPlayer();

        Game game = new Game(players, null);
        game.wrongAnswer();

        assertTrue("Player should be in penalty box", player.isInPenaltyBox());
    }

           /*
        a coin should not be added

        turn is over

        test output

         */





}

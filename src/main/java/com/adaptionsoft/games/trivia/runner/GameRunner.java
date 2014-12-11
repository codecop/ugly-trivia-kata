package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.*;

import java.util.Random;

public class GameRunner {

    public static void main(String[] args) {
        Random rand = new Random();
        play(rand);
    }

    public static void play(Random rand) {
        PlayerUi playerUi = new PlayerUi();
        Players players = new Players(playerUi);
        players.add("Chet");
        players.add("Pat");
        players.add("Sue");

        CurrentPlayer currentPlayer = new CurrentPlayer(players);

        Questions questions = new Questions();

        GameUi gameUi = new GameUi();
        Game aGame = new Game(currentPlayer, questions, gameUi);

        boolean notAWinner;
        do {

            aGame.play(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.correctAnswer();
            }

        } while (notAWinner);
        
    }
}

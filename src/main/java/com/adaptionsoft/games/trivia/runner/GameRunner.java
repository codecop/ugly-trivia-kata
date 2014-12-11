package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.*;

import java.util.Random;

public class GameRunner {

    public static void main(String[] args) {
        Random rand = new Random();
        play(rand);
    }

    public static void play(Random rand) {
        PlayerUI playerUI = new PlayerUI();
        Players players = new Players(playerUI);
        players.add("Chet");
        players.add("Pat");
        players.add("Sue");

        CurrentPlayer currentPlayer = new CurrentPlayer(players);

        Questions questions = new Questions();

        GameUI gameUI = new GameUI();
        Game aGame = new Game(currentPlayer, questions, gameUI);

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

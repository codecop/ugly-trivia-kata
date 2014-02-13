package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Players;
import com.adaptionsoft.games.uglytrivia.Questions;

import java.util.Random;

public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {

        Players players = new Players();
        players.add("Chet");
        players.add("Pat");
        players.add("Sue");

        Questions questions = new Questions();

        Game aGame = new Game(players, questions);

        Random rand = new Random();

        do {

            aGame.playCurrentPlayer(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.correctAnswer();
            }

        } while (notAWinner);

    }
}

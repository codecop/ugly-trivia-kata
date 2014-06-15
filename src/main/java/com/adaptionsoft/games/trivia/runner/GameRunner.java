package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Players;
import com.adaptionsoft.games.uglytrivia.Questions;

import java.util.Random;

public class GameRunner {

    private static boolean notAWinner; // TODO IDEA says can be converted to local variable

    public static void main(String[] args) {
        Random rand = new Random();
        play(rand);
    }

    public static void play(Random rand) {
        
        Players players = new Players();
        players.add("Chet");
        players.add("Pat");
        players.add("Sue");

        Questions questions = new Questions();

        Game aGame = new Game(players, questions);

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

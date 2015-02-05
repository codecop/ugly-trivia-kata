package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.CurrentPlayer;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.GameUi;
import com.adaptionsoft.games.uglytrivia.PlayerUi;
import com.adaptionsoft.games.uglytrivia.Players;
import com.adaptionsoft.games.uglytrivia.Questions;
import java.util.Random;

public class GameRunner {
    // TODO (Checkstyle) Class Data Abstraction Coupling is 8 classes [ConsolePrinter, CurrentPlayer, Game, GameUi, PlayerUi, Players, Questions, Random].

    public static void main(String[] args) {
        Random rand = new Random();
        play(rand);
    }

    public static void play(Random rand) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        PlayerUi playerUi = new PlayerUi(consolePrinter);
        Players players = new Players(playerUi);
        players.add("Chet");
        players.add("Pat");
        players.add("Sue");

        CurrentPlayer currentPlayer = new CurrentPlayer(players);

        Questions questions = new Questions();

        GameUi gameUi = new GameUi(consolePrinter);

        Game game = new Game(currentPlayer, questions, gameUi);
        game.play(rand);
    }

}

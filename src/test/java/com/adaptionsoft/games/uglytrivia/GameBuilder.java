package com.adaptionsoft.games.uglytrivia;

public class GameBuilder {

    public static Game createGameWithSinglePlayer(Player player) {
        CurrentPlayer currentPlayer = new CurrentPlayer();
        currentPlayer.add(player);

        return createGameWith(currentPlayer);
    }

    public static Game createGameWith(CurrentPlayer currentPlayer) {
        return createGameWith(currentPlayer, null);
    }

    public static Game createGameWith(CurrentPlayer currentPlayer, Questions questions) {
        return new Game(currentPlayer, questions);
    }
}

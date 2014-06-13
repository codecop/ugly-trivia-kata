package com.adaptionsoft.games.uglytrivia;

public class GameBuilder {

    public static Game createGameWithSinglePlayer(Player player) {
        Players players = new Players();
        players.add(player);

        return createGameWith(players);
    }

    public static Game createGameWith(Players players) {
        return createGameWith(players, null);
    }

    public static Game createGameWith(Players players, Questions questions) {
        return new Game(players, questions);
    }
}
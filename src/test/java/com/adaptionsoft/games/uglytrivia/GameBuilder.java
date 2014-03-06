package com.adaptionsoft.games.uglytrivia;

public class GameBuilder {
    public static Game createGameWithSinglePlayer(Player player) {
        Players players = new Players();
        players.add(player);
        return new Game(players, null);
    }

}

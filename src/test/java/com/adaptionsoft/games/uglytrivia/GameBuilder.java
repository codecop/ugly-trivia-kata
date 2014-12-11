package com.adaptionsoft.games.uglytrivia;

import static org.mockito.Mockito.mock;

public class GameBuilder {

    public static Game createGameWithSinglePlayer(Player player) {
        PlayerUi ui = mock(PlayerUi.class);
        Players players = new Players(ui);
        players.add(player);
        return createGameWith(players);
    }

    private static Game createGameWith(Players players) {
        return createGameWith(new CurrentPlayer(players));
    }

    public static Game createGameWith(CurrentPlayer currentPlayer) {
        return createGameWith(currentPlayer, null);
    }

    public static Game createGameWith(CurrentPlayer currentPlayer, Questions questions) {
        GameUi ui = new GameUi();
        return new Game(currentPlayer, questions, ui);
    }
}

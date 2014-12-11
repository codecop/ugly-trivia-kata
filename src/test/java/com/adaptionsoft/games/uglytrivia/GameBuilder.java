package com.adaptionsoft.games.uglytrivia;

import static org.mockito.Mockito.mock;

public class GameBuilder {

    public static Game createGameWithSinglePlayer(Player player) {
        PlayerUI ui = mock(PlayerUI.class);
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
        GameUI ui = new GameUI();
        return new Game(currentPlayer, questions, ui);
    }
}

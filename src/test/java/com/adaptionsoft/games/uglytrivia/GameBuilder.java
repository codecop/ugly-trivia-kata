package com.adaptionsoft.games.uglytrivia;

import static org.mockito.Mockito.mock;

public class GameBuilder {
    private static GameUi lastUsedStubbedUi;

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

    public static Game createMockedGameWith(CurrentPlayer currentPlayer) {
        return createMockedGameWith(currentPlayer, null);
    }

    private static Game createMockedGameWith(CurrentPlayer currentPlayer, Questions questions) {
        GameUi ui = mock(GameUi.class);
        lastUsedStubbedUi = ui;
        return createGameWith(currentPlayer, questions, ui);
    }

    public static Game createGameWith(CurrentPlayer currentPlayer, Questions questions) {
        GameUi ui = new GameUi();
        return createGameWith(currentPlayer, questions, ui);
    }

    public static Game createGameWith(CurrentPlayer currentPlayer, Questions questions, GameUi ui) {
        return new Game(currentPlayer, questions, ui);
    }

    public static GameUi mockedUi() {
        return lastUsedStubbedUi;
    }

}

package com.adaptionsoft.games.uglytrivia;

import static org.mockito.Mockito.mock;

public class TestPlayer {
    private static String lastUsedName;
    private static PlayerUI lastUsedStubbedUi;

    public static Player named(String name) {
        lastUsedName = name;
        lastUsedStubbedUi = mock(PlayerUI.class);

        return new Player(name, lastUsedStubbedUi);
    }

    public static String name() {
        return lastUsedName;
    }

    public static PlayerUI mockedUi() {
        return lastUsedStubbedUi;
    }
}

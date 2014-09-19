package com.adaptionsoft.games.uglytrivia;

import static org.mockito.Mockito.mock;

public class TestPlayer {

    private static final PlayerUI STUBBED_UI = mock(PlayerUI.class);

    public static Player named(String name) {
        return new Player(name, STUBBED_UI);
    }
}

package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    // private static final int MAXIMUM_NUMBER_PLAYERS = 6;

    private final List<Player> players = new ArrayList<Player>();
    private PlayerUI showPlayer = new PlayerUI();

    public void add(String playerName) {
        add(new Player(playerName, showPlayer));
    }

    public void add(Player player) {
        // TODO (not in scope) missing check: No check for MAXIMUM_NUMBER_PLAYERS
        players.add(player);
        showPlayer.hasBeenAdded(player.getName(), players.size());
    }

    public Player get(int currentPlayer) {
        return players.get(currentPlayer);
    }

    public int size() {
        return players.size();
    }
}

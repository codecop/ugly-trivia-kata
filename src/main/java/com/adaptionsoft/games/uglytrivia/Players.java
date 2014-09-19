package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    // private static final int MAXIMUM_NUMBER_PLAYERS = 6;

    private List<Player> players = new ArrayList<Player>();
    private Game.UI show;

    public Players() {
        this.show = new Game.UI();
    }

    public void add(Player player) {
        // TODO (not in scope) missing check: No check for MAXIMUM_NUMBER_PLAYERS
        players.add(player);
        show.playerHasBeenAdded(player.getName(), players.size());
    }

    public Player get(int currentPlayer) {
        return players.get(currentPlayer);
    }

    public int size() {
        return players.size();
    }
}

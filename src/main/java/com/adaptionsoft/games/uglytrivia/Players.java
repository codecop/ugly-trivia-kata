package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final int MAXIMUM_NUMBER_PLAYERS = 6;
    
    private List<Player> players = new ArrayList<Player>();
    private int currentPlayer;

    public void add(String playerName) {
        // TODO no check for MAXIMUM_NUMBER_PLAYERS, maybe add?

        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void changeCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

}

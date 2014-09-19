package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

import com.adaptionsoft.games.uglytrivia.Game.UI;

public class CurrentPlayer {
    // private static final int MAXIMUM_NUMBER_PLAYERS = 6;

    private List<Player> players = new ArrayList<Player>();
    private int currentPlayer;
    private UI show;

    public CurrentPlayer() {
        this(new UI());
    }
    
    public CurrentPlayer(UI show) {
        this.show = show;    
    }
    
    public void add(String playerName) {
        add(new Player(playerName));
    }

    public void add(Player player) {
        // TODO (not in scope) missing check: No check for MAXIMUM_NUMBER_PLAYERS
        players.add(player);
        show.playerHasBeenAdded(player.getName(), players.size());
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public void goToPenaltyBox() {
        getCurrentPlayer().goToPenaltyBox();
    }

    public boolean isInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    public void answeredCorrect() {
        getCurrentPlayer().answeredCorrect();
    }

    public boolean didNotWin() {
        return getCurrentPlayer().didPlayerNotWin();
    }

    public void advanceBy(int eyesOfDice) {
        getCurrentPlayer().advanceBy(eyesOfDice);
    }

    public Category currentCategory() {
        return getCurrentPlayer().currentCategory();
    }

    public String getName() {
        return getCurrentPlayer().getName();
    }

    public boolean isGettingOutOfPenaltyBox() {
       return getCurrentPlayer().isGettingOutOfPenaltyBox();
    }

    public void willGetOutOfPenaltyBox() {
        getCurrentPlayer().setGettingOutOfPenaltyBox(true);
    }

    public void willStayInPenaltyBox() {
        getCurrentPlayer().setGettingOutOfPenaltyBox(false);
    }

    public void play(int eyesOfDice) {
        getCurrentPlayer().play(eyesOfDice);
    }
}

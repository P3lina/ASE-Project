package de.p3lina.domain;


import java.util.HashMap;
import java.util.Map;
public class Round {
    public Round(int roundNumber) {
        this.playerThrows = new HashMap<>();
        this.roundNumber = roundNumber;
    }

    private Map<Player, Throw> playerThrows;

    public Map<Player, Throw> getPlayerThrows() {
        return playerThrows;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void addPlayerThrow(Player player, Throw dartThrow) {
        this.playerThrows.put(player, dartThrow);
    }
    private int roundNumber;
}

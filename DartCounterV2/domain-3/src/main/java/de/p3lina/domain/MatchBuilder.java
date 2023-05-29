package de.p3lina.domain;


import java.util.List;

public class MatchBuilder {

    public MatchBuilder(int playerCount, List<Player> players, int startScore, int setCount, int legCount) {
        this.playerCount = playerCount;
        this.players = players;
        this.startScore = startScore;
        this.setCount = setCount;
        this.legCount = legCount;
    }

    private int playerCount;
    private List<Player> players;
    private int startScore;
    private int setCount;
    private int legCount;

    public int getPlayerCount() {
        return playerCount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getStartScore() {
        return startScore;
    }

    public int getSetCount() {
        return setCount;
    }

    public int getLegCount() {
        return legCount;
    }
}
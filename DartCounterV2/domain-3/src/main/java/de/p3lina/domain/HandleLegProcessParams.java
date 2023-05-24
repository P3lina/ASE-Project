package de.p3lina.domain;

import java.util.List;

public class HandleLegProcessParams {

    private List<Player> players;
    private int legNumber;
    private int startScore;

    public HandleLegProcessParams(List<Player> players, int legNumber, int startScore) {
        this.players = players;
        this.legNumber = legNumber;
        this.startScore = startScore;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getLegNumber() {
        return legNumber;
    }

    public void setLegNumber(int legNumber) {
        this.legNumber = legNumber;
    }

    public int getStartScore() {
        return startScore;
    }

    public void setStartScore(int startScore) {
        this.startScore = startScore;
    }
}

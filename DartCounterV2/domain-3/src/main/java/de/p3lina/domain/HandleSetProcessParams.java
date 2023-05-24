package de.p3lina.domain;

import java.util.List;

public class HandleSetProcessParams {

    private List<Player> players;
    private int startScore;
    private int setNumber;
    private int legCount;

    public HandleSetProcessParams(List<Player> players, int startScore, int setNumber, int legCount) {
        this.players = players;
        this.startScore = startScore;
        this.setNumber = setNumber;
        this.legCount = legCount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getStartScore() {
        return startScore;
    }

    public void setStartScore(int startScore) {
        this.startScore = startScore;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getLegCount() {
        return legCount;
    }

    public void setLegCount(int legCount) {
        this.legCount = legCount;
    }
}

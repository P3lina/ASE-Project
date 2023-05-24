package de.p3lina.domain;

public class IsSetWonParameter {
    private Set set;
    private int playerCount;
    private int legCount;

    public IsSetWonParameter(Set set, int playerCount, int legCount) {
        this.set = set;
        this.playerCount = playerCount;
        this.legCount = legCount;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getLegCount() {
        return legCount;
    }

    public void setLegCount(int legCount) {
        this.legCount = legCount;
    }
}

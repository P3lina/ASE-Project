package de.p3lina.domain;


import java.util.ArrayList;
import java.util.List;
public class Match {

    public Match(MatchInfos matchInfos) {
        this.players = matchInfos.getPlayers();
        this.startScore = matchInfos.getStartScore();
        this.setCount = matchInfos.getSetCount();
        this.legCount = matchInfos.getLegCount();
        this.sets = new ArrayList<>();
    }

    private List<Set> sets;
    private int legCount;
    private int setCount;
    private List<Player> players;
    private int startScore;
    private Player winner;

    public void addSet(Set set) {
        this.sets.add(set);
    }

    public List<Set> getSets() {
        return sets;
    }

    public int getLegCount() {
        return legCount;
    }

    public int getSetCount() {
        return setCount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getStartScore() {
        return startScore;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}

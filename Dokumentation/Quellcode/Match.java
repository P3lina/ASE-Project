package de.p3lina.domain;


import java.util.ArrayList;
import java.util.List;
public class Match {

    protected Match(MatchBuilder matchBuilder) {
        this.players = matchBuilder.getPlayers();
        this.startScore = matchBuilder.getStartScore();
        this.setCount = matchBuilder.getSetCount();
        this.legCount = matchBuilder.getLegCount();
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

    public static class MatchBuilder {
        private int legCount;
        private int setCount;
        private List<Player> players;
        private int startScore;
        public MatchBuilder(int legCount, int setCount, List<Player> players, int startScore) {
            this.legCount = legCount;
            this.setCount = setCount;
            this.players = players;
            this.startScore = startScore;
        }
        public Match build() {
            return new Match(this);
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
}

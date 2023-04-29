package de.p3lina.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Leg {


    public Leg(int legNumber, List<Player> players) {
        this.legNumber = legNumber;
        this.playerScore = new HashMap<>();
        this.rounds = new ArrayList<>();
        this.playerAverages = new HashMap<>();
        this.players = players;
        this.playerScoreAtRoundBegin = new HashMap<>();
    }

    private List<Player> players;
    private Statistics statistics;
    private int startScore;
    private Map<Player, Map<Integer, Integer>> playerScoreAtRoundBegin;
    private int indexOfPlayerWhoseTurnItIs;
    private Player winner;
    private Player beginner;
    //Score hier oder auf dem Player?
    private Map<Player, Integer> playerScore;
    private Map<Player, List<Throw>> playerThrows;
    private Map<Player, Double> playerAverageThrow;
    private Map<Player, Integer> playerThrowCount;
    private int legNumber;
    private List<Round> rounds;
    private Map<Player, Double> playerAverages;

    public List<Round> getRounds() {
        return rounds;
    }

    public void addRound(Round round) {
        rounds.add(round);
    }


    public void addPlayerWithScore(Player player, int score) {
        playerScore.put(player, score);
    }
    public void subtractPlayerScore(Player player, int subtractValue) {
        this.playerScore.put(player, this.playerScore.get(player) - subtractValue);
    }
    public void setPlayerScore(Player player, int score) {
        this.playerScore.put(player, score);
    }

    public Player getWinner() {
        return winner;
    }

    public Map<Player, Integer> getPlayerScore() {
        return playerScore;
    }

    public int getLegNumber() {
        return legNumber;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Map<Player, Double> getPlayerAverages() {
        return playerAverages;
    }

    public void setPlayerAverages(Map<Player, Double> playerAverages) {
        this.playerAverages = playerAverages;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public int getStartScore() {
        return startScore;
    }

    public void setStartScore(int startScore) {
        this.startScore = startScore;
    }

    public Map<Player, Map<Integer, Integer>> getPlayerScoreAtRoundBegin() {
        return playerScoreAtRoundBegin;
    }

    public void putPlayerScoreAtRoundBegin(Player player, Map<Integer, Integer> scoreAtRoundBegin) {
        this.playerScoreAtRoundBegin.put(player, scoreAtRoundBegin);
    }
}
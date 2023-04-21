package de.p3lina.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Leg {


    public Leg(int legNumber) {
        this.legNumber = legNumber;
        this.playerScore = new HashMap<>();
        this.rounds = new ArrayList<>();
    }

    private List<Player> players;
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
}
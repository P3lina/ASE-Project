package de.p3lina.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
public class Leg {


    public Leg(int legNumber) {
        this.legNumber = legNumber;
        this.playerScore = new HashMap<>();
        this.rounds = new ArrayList<>();
    }

    private List<Player> players;
    private int indexOfPlayerWhoseTurnItIs;
    @Getter
    @Setter
    private Player winner;
    private Player beginner;
    //Score hier oder auf dem Player?
    @NonNull
    @Getter
    private Map<Player, Integer> playerScore;
    private Map<Player, List<Throw>> playerThrows;
    private Map<Player, Double> playerAverageThrow;
    private Map<Player, Integer> playerThrowCount;
    @Getter
    @NonNull
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

}
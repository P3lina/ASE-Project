package de.p3lina.domain;

import java.util.List;
import java.util.Map;

public class Leg {

    private List<Player> players;
    private int indexOfPlayerWhoseTurnItIs;
    private Player winner;
    private Player beginner;
    //Score hier oder auf dem Player?
    private Map<Player, Integer> playerScore;
    private Map<Player, List<Throw>> playerThrows;
    private Map<Player, Double> playerAverageThrow;
    private Map<Player, Integer> playerThrowCount;


    public void addPlayerWithScore(Player player, int score) {
        playerScore.put(player, score);
    }

}
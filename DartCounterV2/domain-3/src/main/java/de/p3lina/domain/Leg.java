package de.p3lina.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
public class Leg {

    private List<Player> players;
    private int indexOfPlayerWhoseTurnItIs;
    @Getter
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


    public void addPlayerWithScore(Player player, int score) {
        playerScore.put(player, score);
    }

}
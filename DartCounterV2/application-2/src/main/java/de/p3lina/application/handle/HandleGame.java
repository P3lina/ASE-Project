package de.p3lina.application.handle;

import de.p3lina.domain.IsWon;
import de.p3lina.domain.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HandleGame<T> {

    public IsWon isMatchSetWon(List<T> games, Function<T, Player> getWinner, int gameCount, int playerCount) {
        IsWon isWon = new IsWon();
        Map<Player, Integer> playerAndPlayerWinsWithMostGamesWon = getPlayerAndWinsOfPlayerWithMostGamesWon(games, getWinner);
        Player currentWinner = (Player) playerAndPlayerWinsWithMostGamesWon.keySet().toArray()[0];
        int currentWinnerGamesWon = playerAndPlayerWinsWithMostGamesWon.get(currentWinner);
        int gamesNeedToWin = gameCount / playerCount + 1;
        if (currentWinnerGamesWon < gamesNeedToWin) {
            return isWon;
        }
        isWon.setPlayer(currentWinner);
        return isWon;
    }

    private Map<Player, Integer> getPlayerAndWinsOfPlayerWithMostGamesWon(List<T> games, Function<T, Player> getWinner) {
        Map<Player, Integer> playerGameWinCount = new HashMap<>();
        for (T game : games) {
            if (getWinner.apply(game) == null) {
                break;
            }
            Player winner = getWinner.apply(game);
            playerGameWinCount.put(winner, playerGameWinCount.getOrDefault(winner, 0) + 1);
        }
        int highestScore = 0;
        Player winner = null;
        for (Player player : playerGameWinCount.keySet()) {
            if (playerGameWinCount.get(player) > highestScore) {
                highestScore = playerGameWinCount.get(player);
                winner = player;
            }
        }
        return new HashMap<>(Map.of(winner, highestScore));
    }
}

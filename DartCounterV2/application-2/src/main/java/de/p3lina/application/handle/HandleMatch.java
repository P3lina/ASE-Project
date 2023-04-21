package de.p3lina.application.handle;

import de.p3lina.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMatch {


    MessagesDuringMatch message;
    public HandleMatch(MatchInfos matchInfos, MessagesDuringMatch message) {
        this.message = message;
        this.proceedMatch(matchInfos);
    }

    private void proceedMatch(MatchInfos matchInfos) {
        Match match = new Match(matchInfos);
        List<Player> players = match.getPlayers();
        int setNumber = 1;
        while(match.getWinner() == null) {
            HandleSet setHandle = new HandleSet(message);
            Set set = setHandle.proceedSet(players, matchInfos.getStartScore(), setNumber, match.getLegCount());
            setNumber++;
            match.addSet(set);
            //check if match is won
            if(isMatchWon(match).isWon()) {
                match.setWinner(isMatchWon(match).getPlayer());
            }
        }
        message.printPlayerWonMatch(match.getWinner().getName());
    }



    private IsWon isMatchWon(Match match) {
        IsWon isMatchWon = new IsWon();
        int setCount = match.getSetCount();
        int playerCount = match.getPlayers().size();
        Map<Player, Integer> playerAndPlayerWinsWithMostSetsWon = getPlayerAndWinsOfPlayerWithMostSetsWon(match);
        Player currentWinner = (Player) playerAndPlayerWinsWithMostSetsWon.keySet().toArray()[0];
        int currentWinnerSetsWon = playerAndPlayerWinsWithMostSetsWon.get(currentWinner);
        int setsNeedToWin = setCount / playerCount + 1;
        if(currentWinnerSetsWon<setsNeedToWin) {
            return isMatchWon;
        }
        isMatchWon.setPlayer(currentWinner);
        return isMatchWon;
    }





    private Map<Player, Integer> getPlayerAndWinsOfPlayerWithMostSetsWon(Match match) {
        Map<Player, Integer> playerSetWinCount = new HashMap<>();
        for(Set set : match.getSets()) {
            if(set.getWinner()==null){
                break;
            }
            Player winner = set.getWinner();
            if(playerSetWinCount.get(winner)==null) {
                playerSetWinCount.put(winner, 1);
                continue;
            }
            playerSetWinCount.put(winner, playerSetWinCount.get(winner) + 1);
        }
        int highestScore = 0;
        Player winner = null;
        for(Player player : playerSetWinCount.keySet()) {
            if(playerSetWinCount.get(player)>highestScore) {
                highestScore = playerSetWinCount.get(player);
                winner = player;
            }
        }
        return new HashMap(Map.of(winner, highestScore));
    }
}
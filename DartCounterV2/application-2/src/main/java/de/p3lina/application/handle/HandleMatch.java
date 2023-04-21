package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
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
        int setNumber = 0;
        while(match.getWinner() == null) {
            Set set = proceedSet(players, matchInfos.getStartScore(), setNumber);
            setNumber++;
            match.addSet(set);
            //check if match is won
            if(isMatchWon(match).isWon()) {
                match.setWinner(isMatchWon(match).getPlayer());
            }
        }
        message.printPlayerWonMatch(match.getWinner().getName());
    }

    private Set proceedSet(List<Player> players, int startScore, int setNumber) {
        message.printCurrentSetNumber(setNumber);
        Set set = new Set(setNumber);
        int legNumber = 0;
        while (set.getWinner() == null) {
            HandleLeg legHandle = new HandleLeg(message);
            Leg leg = legHandle.processLeg(players, legNumber, startScore);
            set.addLeg(leg);
            if(isSetWon(set, players.size()).isWon()) {
                set.setWinner(isSetWon(set, players.size()).getPlayer());
                break;
            }
            legNumber++;
        }
        message.printPlayerWonSet(set.getWinner().getName(), set.getSetNumber());
        return set;
    }

    private IsWon isMatchWon(Match match) {
        IsWon isMatchWon = new IsWon();
        int setCount = match.getSets().size();
        int playerCount = match.getPlayers().size();
        Map<Player, Integer> playerAndPlayerWinsWithMostSetsWon = getPlayerAndWinsOfPlayerWithMostSetsWon(match);
        Player currentWinner = (Player) playerAndPlayerWinsWithMostSetsWon.keySet().toArray()[0];
        int currentWinnerSetsWon = playerAndPlayerWinsWithMostSetsWon.get(currentWinner);
        int setsNeedToWin = Math.ceilDiv(setCount, playerCount) + 1;
        if(currentWinnerSetsWon<setsNeedToWin) {
            return isMatchWon;
        }
        isMatchWon.setPlayer(currentWinner);
        return isMatchWon;
    }



    private IsWon isSetWon(Set set, int playerCount) {
        IsWon isSetWon = new IsWon();
        int legCount = set.getLegs().size();
        Map<Player, Integer> playerAndPlayerWinsWithMostLegsWon = getPlayerAndWinsOfPlayerWithMostLegsWon(set);
        Player currentWinner = (Player) playerAndPlayerWinsWithMostLegsWon.keySet().toArray()[0];
        int currentWinnerLegsWon = playerAndPlayerWinsWithMostLegsWon.get(currentWinner);
        int legsNeedToWin = Math.ceilDiv(legCount, playerCount) + 1;
        if(currentWinnerLegsWon<legsNeedToWin) {
            return isSetWon;
        }
        isSetWon.setPlayer(currentWinner);
        return isSetWon;
    }

    private Map<Player, Integer> getPlayerAndWinsOfPlayerWithMostLegsWon(Set set) {
        Map<Player, Integer> playerLegWinCount = new HashMap<>();
        for(Leg leg : set.getLegs()) {
            if(leg.getWinner()==null){
                break;
            }
            Player winner = leg.getWinner();
            if(playerLegWinCount.get(winner)==null) {
                playerLegWinCount.put(winner, 1);
                continue;
            }
            playerLegWinCount.put(winner, playerLegWinCount.get(winner) + 1);
        }
        int highestScore = 0;
        Player winner = null;
        for(Player player : playerLegWinCount.keySet()) {
            if(playerLegWinCount.get(player)>highestScore) {
                highestScore = playerLegWinCount.get(player);
                winner = player;
            }
        }
        return new HashMap(Map.of(winner, highestScore));
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
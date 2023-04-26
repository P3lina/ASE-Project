package de.p3lina.application.handle;

import de.p3lina.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleSet {


    private MessagesDuringMatch message;

    public HandleSet(MessagesDuringMatch message) {
        this.message = message;
    }

    public Set proceedSet(List<Player> players, int startScore, int setNumber, int legCount) {
        message.printCurrentSetNumber(setNumber);
        Set set = new Set(setNumber);
        int legNumber = 1;
        while (set.getWinner() == null) {
            HandleLeg legHandle = new HandleLeg(message);
            Leg leg = legHandle.processLeg(players, legNumber, startScore);
            set.addLeg(leg);
            //updated player order
            players = leg.getPlayers();
            if(isSetWon(set, players.size(), legCount).isWon()) {
                set.setWinner(isSetWon(set, players.size(), legCount).getPlayer());
                break;
            }
            legNumber++;
        }
        message.printPlayerWonSet(set.getWinner().getName(), set.getSetNumber());
        return set;
    }



    private IsWon isSetWon(Set set, int playerCount, int legCount) {
        IsWon isSetWon = new IsWon();
        Map<Player, Integer> playerAndPlayerWinsWithMostLegsWon = getPlayerAndWinsOfPlayerWithMostLegsWon(set);
        Player currentWinner = (Player) playerAndPlayerWinsWithMostLegsWon.keySet().toArray()[0];
        int currentWinnerLegsWon = playerAndPlayerWinsWithMostLegsWon.get(currentWinner);
        int legsNeedToWin = legCount / playerCount + 1;
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

}

package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
import de.p3lina.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMatch {


    MessagesDuringMatch message;
    Match match;
    Set currentSet;
    Leg currentLeg;
    public HandleMatch(Match match, MessagesDuringMatch message) {
        this.message = message;
        this.match = match;
        this.currentSet = match.getSets().get(0);
        this.currentLeg = currentSet.getLegs().get(0);
        this.handleMatch();
    }
    private void handleMatch() {
        proceedMatch();
    }

    private void proceedMatch() {
        List<Player> players = this.match.getPlayers();
        while(match.getWinner() == null) {
            proceedSet(currentSet, players);
            //check if match is won
            if(isMatchWon().isWon()) {
                match.setWinner(isMatchWon().getPlayer());
            }else {
                this.currentSet = match.getSets().get(currentSet.getSetNumber());
                this.currentLeg = this.currentSet.getLegs().get(0);
            }
        }
        message.printPlayerWonMatch(match.getWinner().getName());
    }

    private void proceedSet(Set currentSet, List<Player> players) {
        message.printCurrentSetNumber(currentSet.getSetNumber());
        while (currentSet.getWinner() == null) {
            processLeg(players);
            if(isSetWon().isWon()) {
                currentSet.setWinner(isSetWon().getPlayer());
                break;
            }
            this.currentLeg = currentSet.getLegs().get(currentLeg.getLegNumber());
        }
        message.printPlayerWonSet(currentSet.getWinner().getName(), currentSet.getSetNumber());
    }

    private IsWon isMatchWon() {
        IsWon isMatchWon = new IsWon();
        int setCount = match.getSets().size();
        int playerCount = this.match.getPlayers().size();
        Map<Player, Integer> playerAndPlayerWinsWithMostSetsWon = getPlayerAndWinsOfPlayerWithMostSetsWon();
        Player currentWinner = (Player) playerAndPlayerWinsWithMostSetsWon.keySet().toArray()[0];
        int currentWinnerSetsWon = playerAndPlayerWinsWithMostSetsWon.get(currentWinner);
        int setsNeedToWin = setCount / playerCount + 1;
        if(currentWinnerSetsWon<setsNeedToWin) {
            return isMatchWon;
        }
        isMatchWon.setPlayer(currentWinner);
        return isMatchWon;
    }



    private IsWon isSetWon() {
        IsWon isSetWon = new IsWon();
        int legCount = this.currentSet.getLegs().size();
        int playerCount = this.match.getPlayers().size();
        Map<Player, Integer> playerAndPlayerWinsWithMostLegsWon = getPlayerAndWinsOfPlayerWithMostLegsWon();
        Player currentWinner = (Player) playerAndPlayerWinsWithMostLegsWon.keySet().toArray()[0];
        int currentWinnerLegsWon = playerAndPlayerWinsWithMostLegsWon.get(currentWinner);
        int legsNeedToWin = legCount / playerCount + 1;
        if(currentWinnerLegsWon<legsNeedToWin) {
            return isSetWon;
        }
        isSetWon.setPlayer(currentWinner);
        return isSetWon;
    }

    private Map<Player, Integer> getPlayerAndWinsOfPlayerWithMostLegsWon() {
        Map<Player, Integer> playerLegWinCount = new HashMap<>();
        for(Leg leg : currentSet.getLegs()) {
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

    private Map<Player, Integer> getPlayerAndWinsOfPlayerWithMostSetsWon() {
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

    private void processLeg(List<Player> players) {
        message.printCurrentLegNumber(currentLeg.getLegNumber());
        while (this.currentLeg.getWinner() == null) {
            processRound(players);
        }
        System.out.println("Player " + currentLeg.getWinner().getName() + " won!");
    }

    private void processRound(List<Player> players) {
        for(Player player : players) {
            ThrowStatus throwStatus = processThrow(player);
            if(throwStatus == ThrowStatus.CHECKOUT) {
                currentLeg.setWinner(player);
                break;
            }
        }
    }

    private ThrowStatus processThrow(Player player) {
        int playerScoreBeforeThrow = currentLeg.getPlayerScore().get(player);
        for(int i = 0; i<3; i++) {
            HandleDart dartHandle = new HandleDart(player, message, playerScoreBeforeThrow, currentLeg);
            DartStatus dartStatus = dartHandle.processDart();
            if(dartStatus==DartStatus.BUSTED) {
                this.currentLeg.setPlayerScore(player, playerScoreBeforeThrow);
                return ThrowStatus.NOTHING;
            }
            if(dartStatus==DartStatus.CHECKOUT) {
                return ThrowStatus.CHECKOUT;
            }
        }
        return ThrowStatus.NOTHING;
    }
}

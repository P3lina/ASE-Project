package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
import de.p3lina.domain.*;

import java.util.ArrayList;
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
            this.currentSet = match.getSets().get(currentSet.getSetNumber());
            this.currentLeg = this.currentSet.getLegs().get(0);
            //check if match is won
        }
        //Print that player x has won
        //make sure the application ends
    }

    private void proceedSet(Set currentSet, List<Player> players) {
        message.printCurrentSetNumber(currentSet.getSetNumber());
        while (currentSet.getWinner() == null) {
            processLeg(players);
            boolean isSetWon = setWinnerOfCurrentSet(currentSet.getLegs().size(), players.size());
            if(isSetWon) {
                break;
            }
            this.currentLeg = currentSet.getLegs().get(currentLeg.getLegNumber());
        }
        message.printPlayerWonSet(currentSet.getWinner().getName(), currentSet.getSetNumber());
    }


    private boolean setWinnerOfCurrentSet(int legCount, int playerCount) {
        Map<Player, Integer> playerAndPlayerWinsWithMostLegsWon = getPlayerAndWinsOfPlayerWithMostLegsWon();
        Player currentWinner = (Player) playerAndPlayerWinsWithMostLegsWon.keySet().toArray()[0];
        int currentWinnerLegsWon = playerAndPlayerWinsWithMostLegsWon.get(currentWinner);
        int legsNeedToWin = legCount / playerCount + 1;
        if(currentWinnerLegsWon<legsNeedToWin) {
            return false;
        }
        currentSet.setWinner(currentWinner);
        return true;
    }

    private Map<Player, Integer> getPlayerAndWinsOfPlayerWithMostLegsWon() {
        Map<Player, Integer> playerLegWinCount = new HashMap<Player, Integer>();
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
            DartStatus dartStatus = processDart(player, playerScoreBeforeThrow);
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

    private DartStatus processDart(Player player, int playerScoreBeforeThrow) {
        message.printPlayerInputDart(player.getName());
        UserInput userInput = UserInput.prepareUserDartInput(new UserCommunicationService().getUserInput().toString());
        if(!userInput.isValidDart(userInput)) {
            return processDart(player, playerScoreBeforeThrow);
        }
        PossibleDarts parsedInput = PossibleDarts.valueOf(userInput.toString());
        Dart dart = new Dart(parsedInput);
        message.printThrow(player.getName(), dart.getPoints());
        if(playerBusted(player, dart)) {
            message.printPlayerBusted(player.getName(), playerScoreBeforeThrow);
            return DartStatus.BUSTED;
        }
        if(playerCheckedOut(player, dart)) {
            message.printPlayerCheckedOut(player.getName());
            return DartStatus.CHECKOUT;
        }
        this.currentLeg.subtractPlayerScore(player, dart.getPoints());
        message.printRemainingScore(this.currentLeg.getPlayerScore().get(player));
        return DartStatus.NOTHING;
    }

    private boolean playerBusted(Player player, Dart dart) {
        if(dart.getPoints()>this.currentLeg.getPlayerScore().get(player)) {
            return true;
        }
        if(this.currentLeg.getPlayerScore().get(player) - dart.getPoints() == 1) {
            return true;
        }
        if(this.currentLeg.getPlayerScore().get(player) - dart.getPoints() == 0 && !dart.isDoubleNumber()) {
            return true;
        }
        return false;
    }

    private boolean playerCheckedOut(Player player, Dart dart) {
        if(dart.getPoints()==this.currentLeg.getPlayerScore().get(player)&&dart.isDoubleNumber()) {
            return true;
        }
        return false;
    }
}

package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.domain.*;

import java.util.List;

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
        proceedMatch(this.match, this.currentSet, this.currentLeg, match.getPlayers());
    }

    private void proceedMatch(Match match, Set currentSet, Leg currentLeg, List<Player> players) {
        while(match.getWinner() == null) {
            proceedSet(currentSet, players);
        }
    }

    private void proceedSet(Set currentSet, List<Player> players) {
        message.printCurrentSetNumber();
        while (currentSet.getWinner() == null) {
            processLeg(players);
        }
    }

    private void processLeg(List<Player> players) {
        message.printCurrentLegNumber();
        while (this.currentLeg.getWinner() == null) {
            processRound(players);
        }
        System.out.println("Player " + currentLeg.getWinner().getName() + " won!");
        this.currentLeg = currentSet.getLegs().get(currentLeg.getLegNumber());
    }

    private void processRound(List<Player> players) {
        for(Player player : players) {
            ThrowStatus throwStatus = processThrow(player);
            if(throwStatus == ThrowStatus.CHECKOUT) {
                break;
            }
        }
    }

    private ThrowStatus processThrow(Player player) {
        int playerScoreBeforeThrow = currentLeg.getPlayerScore().get(player);
        for(int i = 0; i<3; i++) {
            DartStatus dartStatus = processDart(player, playerScoreBeforeThrow);
            if(dartStatus==DartStatus.BUSTED) {
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
        String userInput = new UserCommunicationService().getUserInput().toString();
        userInput = prepareUserInput(userInput);
        try {
            PossibleDarts parsedInput = PossibleDarts.valueOf(userInput);
            Dart dart = new Dart(parsedInput);
            if(playerBusted(player, dart)) {
                this.currentLeg.setPlayerScore(player, playerScoreBeforeThrow);
                return DartStatus.BUSTED;
            }
            if(playerCheckedOut(player, dart)) {
                currentLeg.setWinner(player);
                return DartStatus.CHECKOUT;
            }
            //System.out.println("You threw: " + dart.getPoints());
            message.printThrow(player.getName(), dart.getPoints());
            this.currentLeg.subtractPlayerScore(player, dart.getPoints());
            System.out.println("remaining: " + this.currentLeg.getPlayerScore().get(player));
        }catch(IllegalArgumentException exc) {
            processDart(player, playerScoreBeforeThrow);
        }
        return DartStatus.NOTHING;
    }

    private boolean playerBusted(Player player, Dart dart) {
        if(dart.getPoints()>this.currentLeg.getPlayerScore().get(player)) {
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

    //should be moved to adapters layer in the future
    private String prepareUserInput(String userInput) {
        if(userInput.length()==0) {
            return userInput;
        }
        if(Character.isDigit(userInput.charAt(0)) && userInput.length()==1 && Integer.parseInt(userInput)==0) {
            return "Zero";
        }
        if(!Character.isDigit(userInput.charAt(0))) {
            return userInput.substring(0, 1).toUpperCase() + userInput.substring(1);
        }
        StringBuilder sb = new StringBuilder("S");
        sb.append(userInput);
        return sb.toString();
    }
}

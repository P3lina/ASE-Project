package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.domain.*;

import java.util.ArrayList;
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
            proceedSet(currentSet, currentLeg, players);
        }
    }

    private void proceedSet(Set currentSet, Leg currentLeg, List<Player> players) {
        message.printCurrentSetNumber();
        while (currentSet.getWinner() == null) {
            processLeg(currentLeg, players);
        }
    }

    private void processLeg(Leg currentLeg, List<Player> players) {
        message.printCurrentLegNumber();
        while (currentLeg.getWinner() == null) {
            processRound(players);
        }
    }

    private void processRound(List<Player> players) {
        for(Player player : players) {
            processThrow(player);
        }
    }

    private void processThrow(Player player) {
        int playerScoreBeforeThrow = currentLeg.getPlayerScore().get(player);
        for(int i = 0; i<3; i++) {
            processDart(player, playerScoreBeforeThrow);
        }
    }

    private void processDart(Player player, int playerScoreBeforeThrow) {
        message.printPlayerInputDart(player.getName());
        String userInput = new UserCommunicationService().getUserInput().toString();
        userInput = prepareUserInput(userInput);
        try {
            PossibleDarts parsedInput = PossibleDarts.valueOf(userInput);
            Dart dart = new Dart(parsedInput);
            if(playerOverthrown(player, dart)) {
                this.currentLeg.setPlayerScore(player, playerScoreBeforeThrow);
                return;
            }
            System.out.println("You threw: " + dart.getPoints());
            this.currentLeg.subtractPlayerScore(player, dart.getPoints());
            System.out.println("remaining: " + this.currentLeg.getPlayerScore().get(player));
        }catch(IllegalArgumentException exc) {
            processDart(player, playerScoreBeforeThrow);
        }
    }

    private boolean playerOverthrown(Player player, Dart dart) {
        if(dart.getPoints()>this.currentLeg.getPlayerScore().get(player)) {
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

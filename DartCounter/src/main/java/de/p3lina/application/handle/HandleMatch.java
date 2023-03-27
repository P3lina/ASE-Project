package de.p3lina.application.handle;

import de.p3lina.domain.*;

import java.util.ArrayList;
import java.util.List;

public class HandleMatch {


    MessagesDuringMatch message;
    public HandleMatch(MessagesDuringMatch message) {
        this.message = message;
        this.handleMatch();
    }
    public void handleMatch() {
        Match match = null;
        Set currentSet = null;
        Leg currentLeg = null;
        List<Player> players = new ArrayList<>();
        while(match.getWinner() == null) {
            message.printCurrentSetNumber();
            while (currentSet.getWinner() == null) {
                message.printCurrentLegNumber();
                while (currentLeg.getWinner() == null) {
                    processRound(players);
                }
            }
        }
    }

    private void processRound(List<Player> players) {
        for(Player player : players) {
            processThrow(player);
        }
    }

    private void processThrow(Player player) {
        for(int i = 0; i<3; i++) {
            processDart(player);
        }
    }

    private void processDart(Player player) {
        message.printPlayerInputDart(player.getName());
    }

}

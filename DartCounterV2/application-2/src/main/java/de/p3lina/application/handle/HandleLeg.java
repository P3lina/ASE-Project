package de.p3lina.application.handle;

import de.p3lina.domain.Leg;
import de.p3lina.domain.MessagesDuringMatch;
import de.p3lina.domain.Player;
import de.p3lina.domain.Round;

import java.util.List;

public class HandleLeg {

    private Leg currentLeg;
    private MessagesDuringMatch message;
    public HandleLeg(Leg currentLeg, MessagesDuringMatch message) {
        this.currentLeg = currentLeg;
        this.message = message;
    }

    public void processLeg(List<Player> players) {
        message.printCurrentLegNumber(currentLeg.getLegNumber());
        int roundNumber = 0;
        while (this.currentLeg.getWinner() == null) {
            HandleRound roundHandle = new HandleRound(players, currentLeg, message);
            Round round = roundHandle.processRound(roundNumber);
            roundNumber++;
            currentLeg.addRound(round);
        }
        System.out.println("Player " + currentLeg.getWinner().getName() + " won!");
    }

}

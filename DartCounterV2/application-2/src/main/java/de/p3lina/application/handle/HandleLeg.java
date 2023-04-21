package de.p3lina.application.handle;

import de.p3lina.domain.Leg;
import de.p3lina.domain.MessagesDuringMatch;
import de.p3lina.domain.Player;
import de.p3lina.domain.Round;

import java.util.List;

public class HandleLeg {

    private MessagesDuringMatch message;
    public HandleLeg(MessagesDuringMatch message) {
        this.message = message;
    }

    public Leg processLeg(List<Player> players, int legNumber, int startScore) {
        Leg leg = new Leg(legNumber);
        initPlayerScore(leg, players, startScore);
        message.printCurrentLegNumber(leg.getLegNumber());
        int roundNumber = 0;
        while (leg.getWinner() == null) {
            HandleRound roundHandle = new HandleRound(players, leg, message);
            Round round = roundHandle.processRound(roundNumber);
            roundNumber++;
            leg.addRound(round);
        }
        System.out.println("Player " + leg.getWinner().getName() + " won!");
        return leg;
    }

    private void initPlayerScore(Leg leg, List<Player> players, int startScore) {
        for(Player player : players) {
            leg.setPlayerScore(player, startScore);
        }
    }


}

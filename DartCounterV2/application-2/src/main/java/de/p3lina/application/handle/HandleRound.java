package de.p3lina.application.handle;

import de.p3lina.application.PlayerAverageCalculator;
import de.p3lina.domain.*;

import java.util.List;

public class HandleRound {

    private List<Player> players;
    private Leg currentLeg;
    private MessagesDuringMatch message;

    public HandleRound(List<Player> players, Leg currentLeg, MessagesDuringMatch message) {
        this.players = players;
        this.currentLeg = currentLeg;
        this.message = message;
    }
    public Round processRound(Round round) {
        for(Player player : players) {
            HandleThrow throwHandle = new HandleThrow(player, currentLeg, message);
            Throw dartThrow = throwHandle.processThrow(currentLeg);
            round.addPlayerThrow(player, dartThrow);
            boolean checkOut = dartThrow.isCheckedOut();
            if(checkOut) {
                currentLeg.setWinner(player);
                break;
            }
            PlayerAverageCalculator averageCalculator = new PlayerAverageCalculator();
            Double playerThrowAverage = averageCalculator.getPlayerAverageOfRound(round, player);
            Double playerLegAverage = averageCalculator.getPlayerAverageOfLeg(player, currentLeg);
            message.printPlayerRoundAverage(player.getName(), playerThrowAverage, playerLegAverage);
        }
        return round;
    }



}

package de.p3lina.application.handle;

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
    public Round processRound(int roundNumber) {
        Round round = new Round(roundNumber);
        for(Player player : players) {
            HandleThrow throwHandle = new HandleThrow(player, currentLeg, message);
            Throw dartThrow = throwHandle.processThrow(currentLeg);
            round.addPlayerThrow(player, dartThrow);
            boolean checkOut = dartThrow.isCheckedOut();
            if(checkOut) {
                currentLeg.setWinner(player);
                break;
            }
        }
        return round;
    }



}

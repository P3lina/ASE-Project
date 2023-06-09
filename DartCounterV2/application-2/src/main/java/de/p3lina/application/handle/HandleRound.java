package de.p3lina.application.handle;

import de.p3lina.application.PlayerAverageCalculator;
import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

import java.util.List;

public class HandleRound implements Handle<Round, Round, Object, Object>{

    private List<Player> players;
    private Leg currentLeg;
    private MessagesDuringMatch message;

    public HandleRound(List<Player> players, Leg currentLeg, MessagesDuringMatch message) {
        this.players = players;
        this.currentLeg = currentLeg;
        this.message = message;
    }
    public Round process(Round round) {
        for(Player player : players) {
            HandleThrow throwHandle = new HandleThrow(player, currentLeg, message);
            Throw dartThrow = throwHandle.process(currentLeg);
            round.addPlayerThrow(player, dartThrow);
            boolean checkOut = dartThrow.isCheckedOut();
            if(checkOut) {
                currentLeg.setWinner(player);
                initPlayerThrowInRound(players, round);
                break;
            }
            PlayerAverageCalculator averageCalculator = new PlayerAverageCalculator();
            Double playerThrowAverage = averageCalculator.getPlayerAverageOfRound(round, player);
            Double playerLegAverage = averageCalculator.getPlayerAverageOfLeg(player, currentLeg);
            message.printPlayerRoundAverage(player.getName(), playerThrowAverage, playerLegAverage);
        }
        return round;
    }

    @Override
    public Object isMatchSetWon(Object something) {
        return null;
    }


    private void initPlayerThrowInRound(List<Player> players, Round round) {
        for(Player player : players) {
            if(round.getPlayerThrows().get(player)==null) {
                round.addPlayerThrow(player, new Throw());
            }
        }
    }

}

package de.p3lina.adapters;

import de.p3lina.adapters.i18n.I18n;
import de.p3lina.domain.MessagesDuringMatch;
import de.p3lina.domain.i18n.Messages;

public class MatchMessages implements MessagesDuringMatch {


    @Override
    public void printCurrentSetNumber() {

    }

    @Override
    public void printCurrentLegNumber() {

    }

    @Override
    public void printStatistics() {
        System.out.println("statistics");
    }

    @Override
    public void printRemainingScore(int remainingScore) {
        System.out.println(remainingScore + " points remaining");
    }

    @Override
    public void printWhoseTurnItIs(String playerName) {
        System.out.println("It's " + playerName + "'s turn");
    }

    @Override
    public void printPlayerInputDart(String playerName) {
        System.out.println(playerName + " please input your dart");
    }

    @Override
    public void printThrow(String name, int points) {
        System.out.println(I18n.getMessage(Messages.PLAYER_THROW, name, points));
    }


}

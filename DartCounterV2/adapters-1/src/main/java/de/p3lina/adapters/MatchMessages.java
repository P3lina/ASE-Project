package de.p3lina.adapters;

import de.p3lina.adapters.i18n.I18n;
import de.p3lina.domain.MessagesDuringMatch;
import de.p3lina.domain.i18n.Messages;

public class MatchMessages implements MessagesDuringMatch {


    @Override
    public void printCurrentSetNumber(int setNumber) {
        System.out.println(I18n.getMessage(Messages.SET_NUMBER, setNumber));
    }

    @Override
    public void printCurrentLegNumber(int legNumber) {
        System.out.println(I18n.getMessage(Messages.LEG_NUMBER, legNumber));
    }

    @Override
    public void printStatistics() {
        System.out.println("statistics");
    }

    @Override
    public void printRemainingScore(int remainingScore) {
        System.out.println(I18n.getMessage(Messages.REMAINING_SCORE, remainingScore));
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

    @Override
    public void printPlayerBusted(String name, int resetPointsTo) {
        System.out.println(I18n.getMessage(Messages.PLAYER_BUSTED, name, resetPointsTo));
    }

    @Override
    public void printPlayerCheckedOut(String name) {
        System.out.println(I18n.getMessage(Messages.PLAYER_CHECKED_OUT, name));
    }

    @Override
    public void printPlayerWonSet(String name, int setNumber) {
        System.out.println(I18n.getMessage(Messages.PLAYER_WON_SET, name, setNumber));
    }


}

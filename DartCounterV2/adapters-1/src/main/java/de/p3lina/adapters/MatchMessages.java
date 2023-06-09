package de.p3lina.adapters;

import de.p3lina.adapters.i18n.I18n;
import de.p3lina.domain.messages.MessagesDuringMatch;
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
    public void printRemainingScore(int remainingScore) {
        System.out.println(I18n.getMessage(Messages.REMAINING_SCORE, remainingScore));
    }

    @Override
    public void printPlayerInputDart(String playerName) {
        System.out.println(playerName + " please input your dart");
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

    @Override
    public void printPlayerWonMatch(String name) {
        System.out.println(I18n.getMessage(Messages.PLAYER_WON_MATCH, name));
    }

    @Override
    public void printPlayerWonLeg(String name, int legNumber) {
        System.out.println(I18n.getMessage(Messages.PLAYER_WON_LEG, name, legNumber));
    }

    @Override
    public void printPlayerAverages(String name, Double average) {
        System.out.println(I18n.getMessage(Messages.PLAYER_AVERAGES, name, average));
    }

    @Override
    public void printPlayerRoundAverage(String name, Double average, Double playerLegAverage) {
        System.out.println(I18n.getMessage(Messages.PLAYER_ROUND_AVERAGE, name, average, playerLegAverage));
    }

    @Override
    public void printPlayerCheckoutQuote(String name, int legNumber, Double playerCheckoutQuote) {
        System.out.println(I18n.getMessage(Messages.PLAYER_CHECKOUT_QUOTE, name, legNumber, playerCheckoutQuote));
    }


}

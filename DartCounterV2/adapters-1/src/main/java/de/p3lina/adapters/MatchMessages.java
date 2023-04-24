package de.p3lina.adapters;

import de.p3lina.adapters.i18n.I18n;
import de.p3lina.domain.MessagesDuringMatch;
import de.p3lina.domain.Player;
import de.p3lina.domain.i18n.Messages;

import java.util.Map;

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


}

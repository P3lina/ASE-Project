package de.p3lina.application;

import de.p3lina.domain.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchHistory {


    public MatchHistory(Match match) {
        saveMatchHistory(match);
    }

    private void saveMatchHistory(Match match) {
        String matchString = getMatchString(match);
        System.out.println(matchString);
    }
    private String getMatchString(Match match) {
        StringBuilder matchString = new StringBuilder("Match History:\n");
        for(Set set : match.getSets()) {
            matchString.append(getSetString(set));
        }
        return matchString.toString();
    }
    private String getSetString(Set set) {
        StringBuilder setString = new StringBuilder("Set No. " + set.getSetNumber() + ":\n");
        for(Leg leg : set.getLegs()) {
            setString.append(getLegString(leg));
            setString.append(getPlayerAverages(leg));
        }
        return setString.toString();
    }
    private String getPlayerAverages(Leg leg) {
        StringBuilder playerAveragesString = new StringBuilder("Averages:\n");
        PlayerAverageCalculator averageCalculator = new PlayerAverageCalculator();
        Map<Player, Double> playerAverages = averageCalculator.getPlayersAveragesOfLeg(leg);
        for(Player player : playerAverages.keySet()) {
            playerAveragesString.append(player.getName() + ": " + playerAverages.get(player) + "\n");
        }
        return playerAveragesString.toString();
    }
    private String getLegString(Leg leg) {
        StringBuilder legString = new StringBuilder("Leg No. " + leg.getLegNumber() + ":\n");
        for(Round round : leg.getRounds()) {
            legString.append(getRoundString(round, leg));
        }
        return legString.toString();
    }
    private String getRoundString(Round round, Leg leg) {
        StringBuilder roundString = new StringBuilder("Round No. " + round.getRoundNumber() + ":\n");
        for(Player player : round.getPlayerThrows().keySet()) {
            roundString.append(player.getName() + ": " + getThrowString(round.getPlayerThrows().get(player), player, leg, round));
        }
        return roundString.toString();
    }
    private String getThrowString(Throw dartThrow, Player player, Leg leg, Round round){
        StringBuilder dartString = new StringBuilder("");
        AtomicInteger index = new AtomicInteger(0);
        dartThrow.getDarts().forEach(dart -> {
            if (index.getAndIncrement() > 0) {
                dartString.append(',');
            }
            dartString.append(dart.getDart().toString());
        });
        dartString.append(isCheckoutThrow(leg, player, round)?" -> checkout!":"");
        dartString.append("\n");
        return dartString.toString();
    }

    private boolean isCheckoutThrow(Leg leg, Player player, Round round) {
        if(leg.getPlayerScore().get(player)==0 && leg.getRounds().get(leg.getRounds().size()-1)==round) {
            return true;
        }
        return false;
    }

    public void printMatchHistory() {

    }
}
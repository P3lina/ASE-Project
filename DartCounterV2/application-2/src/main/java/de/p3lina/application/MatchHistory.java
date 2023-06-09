package de.p3lina.application;

import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesOutsideMatch;

import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchHistory {


    public MatchHistory() {
    }

    public void saveMatchHistory(Match match, MessagesOutsideMatch messagesOutsideMatch) {
        boolean shouldMatchHistoryBeSaved = messagesOutsideMatch.askForSaveHistory();
        if(!shouldMatchHistoryBeSaved){
            return;
        }
        String matchHistoryString = getMatchHistoryString(match);
        try {
            FileWriter fileWriter = new FileWriter("matchHistory.txt");
            fileWriter.write(matchHistoryString);
            fileWriter.close();
            System.out.println("History has been saved!");
        }catch(Exception exc) {
            System.out.println("History could not be saved!");
        }
    }
    public String getMatchHistoryString(Match match) {
        StringBuilder matchString = new StringBuilder("Match History:\n");
        for(Set set : match.getSets()) {
            matchString.append(getSetString(set));
        }
        matchString.append("\t-> ").append(match.getWinner().getName()).append(" won the match!");
        return matchString.toString();
    }
    private String getSetString(Set set) {
        StringBuilder setString = new StringBuilder("\tSet No. " + set.getSetNumber() + ":\n");
        for(Leg leg : set.getLegs()) {
            setString.append(getLegString(leg));
            setString.append(getPlayerAverages(leg));
        }
        return setString.toString();
    }
    private String getLegString(Leg leg) {
        StringBuilder legString = new StringBuilder("\t\tLeg No. " + leg.getLegNumber() + ":\n");
        for(Round round : leg.getRounds()) {
            legString.append(getRoundString(round, leg));
        }
        legString.append(legLegCheckoutQuoteString(leg));
        return legString.toString();
    }
    private String getRoundString(Round round, Leg leg) {
        StringBuilder roundString = new StringBuilder("\t\t\tRound No. " + round.getRoundNumber() + ":\n");
        for(Player player : round.getPlayerThrows().keySet()) {
            roundString.append("\t\t\t\t").append(player.getName()).append(": ").append(getThrowString(round.getPlayerThrows().get(player), player, leg, round));
        }
        return roundString.toString();
    }
    private String getThrowString(Throw dartThrow, Player player, Leg leg, Round round){
        StringBuilder dartString = new StringBuilder();
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
        return leg.getPlayerScore().get(player) == 0 && leg.getRounds().get(leg.getRounds().size() - 1) == round;
    }

    private String getPlayerAverages(Leg leg) {
        StringBuilder playerAveragesString = new StringBuilder("\t\t\tAverages:\n");
        Map<Player, Double> playerAverages = leg.getStatistics().getAverage();
        for(int index = 0; index < playerAverages.keySet().size(); index++) {
            Player player = playerAverages.keySet().stream().toList().get(index);
            playerAveragesString.append("\t\t\t\t")
                    .append(player.getName())
                    .append(": ")
                    .append(playerAverages.get(player))
                    .append(breakLineIfNotEqual(playerAverages.keySet().size(), index));
        }
        return playerAveragesString.toString();
    }

    private String breakLineIfNotEqual(int number1, int number2) {
        if(number1==number2) {
            return "";
        }
        return "\n";
    }

    private String legLegCheckoutQuoteString(Leg leg) {
        StringBuilder checkoutQuoteStringBuilder = new StringBuilder();
        Map<Player, Map<Integer, Double>> playerCheckoutQuote = leg.getStatistics().getCheckoutQuote();
        for(Player player : playerCheckoutQuote.keySet()) {
            if(playerCheckoutQuote.get(player).get(leg.getLegNumber())==0.0) {
                continue;
            }
            checkoutQuoteStringBuilder.append("\t\t\t")
                            .append("Checkout Quote:\n")
                            .append("\t\t\t\t")
                            .append(player.getName())
                            .append(": ")
                            .append(playerCheckoutQuote.get(player).get(leg.getLegNumber())*100)
                            .append("%")
                            .append("\n");
            return checkoutQuoteStringBuilder.toString();
        }
        return "";
    }
}
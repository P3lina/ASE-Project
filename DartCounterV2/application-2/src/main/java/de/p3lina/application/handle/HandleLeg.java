package de.p3lina.application.handle;

import de.p3lina.application.PlayerAverageCalculator;
import de.p3lina.application.PlayerCheckoutQuoteCalculator;
import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

import java.util.*;

public class HandleLeg implements Handle<Leg, HandleLegProcessParams, Object, Object> {

    private MessagesDuringMatch message;
    public HandleLeg(MessagesDuringMatch message) {
        this.message = message;
    }

    public Leg process(HandleLegProcessParams params) {
        List<Player> players = params.getPlayers();
        int legNumber = params.getLegNumber();
        int startScore = params.getStartScore();
        Leg leg = new Leg(legNumber, players);
        leg.setStartScore(startScore);
        initPlayerScore(leg, players, startScore);
        initPlayerScoreAtRoundBegin(leg, players, startScore);
        message.printCurrentLegNumber(leg.getLegNumber());
        int roundNumber = 1;
        while (leg.getWinner() == null) {
            HandleRound roundHandle = new HandleRound(players, leg, message);
            Round round = new Round(roundNumber);
            leg.addRound(round);
            roundHandle.process(round);
            roundNumber++;
        }
        PlayerAverageCalculator averageCalculator = new PlayerAverageCalculator();
        PlayerCheckoutQuoteCalculator checkoutQuoteCalculator = new PlayerCheckoutQuoteCalculator(leg);
        Statistics statistics = new Statistics();
        statistics.setAverage(averageCalculator.getPlayersAveragesOfLeg(leg));
        //set checkout quote
        statistics.setCheckoutQuote(checkoutQuoteCalculator.getPlayersCheckoutQuoteOfLeg());
        leg.setStatistics(statistics);
        leg.setPlayers(getPlayerOrderForNextLeg(leg));
        message.printPlayerWonLeg(leg.getWinner().getName(), leg.getLegNumber());
        printPlayerCheckoutQuote(leg, leg.getStatistics().getCheckoutQuote());
        printPlayerAverages(leg.getStatistics().getAverage());
        return leg;
    }

    @Override
    public Object isMatchSetWon(Object something) {
        return null;
    }

    private void printPlayerAverages(Map<Player, Double> playerAverages) {
        for(Player player : playerAverages.keySet()) {
            Double average = playerAverages.get(player);
            message.printPlayerAverages(player.getName(), average);
        }
    }

    private void printPlayerCheckoutQuote(Leg leg, Map<Player, Map<Integer, Double>> playerCheckoutQuote) {
        for(Player player : playerCheckoutQuote.keySet()) {
            if(playerCheckoutQuote.get(player).get(leg.getLegNumber())==0.0) {
                continue;
            }
            int legNumber = leg.getLegNumber();
            Double checkoutQuote = playerCheckoutQuote.get(player).get(legNumber)*100;
            message.printPlayerCheckoutQuote(player.getName(), legNumber, checkoutQuote);
        }
    }

    private void initPlayerScore(Leg leg, List<Player> players, int startScore) {
        for(Player player : players) {
            leg.setPlayerScore(player, startScore);
        }
    }

    private List<Player> getPlayerOrderForNextLeg(Leg leg) {
        List<Map.Entry<Player, Integer>> list = new ArrayList<>(leg.getPlayerScore().entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        List<Player> players = new ArrayList<>();
        for(Map.Entry<Player, Integer> entry : list) {
            players.add(entry.getKey());
        }
        return players;
    }

    private void initPlayerScoreAtRoundBegin(Leg leg, List<Player> players, int startScore) {
        for(Player player : players) {
            leg.putPlayerScoreAtRoundBegin(player, new HashMap<>(1, startScore));
        }
    }


}

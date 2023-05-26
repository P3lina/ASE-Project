package de.p3lina.application;

import de.p3lina.domain.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerCheckoutQuoteCalculator {

    private Leg leg;
    public PlayerCheckoutQuoteCalculator(Leg leg) {
        this.leg = leg;
        fillPlayerScoreAtRoundBeginMap();
    }


    public Map<Player, Map<Integer, Double>> getPlayersCheckoutQuoteOfLeg() {
        Map<Player, Map<Integer, Double>> playersCheckoutQuotePL = new HashMap<>();
        for(Player player : leg.getPlayers()) {
            playersCheckoutQuotePL.put(player, getPlayerCheckoutQuoteOfLeg(player));
        }
        return playersCheckoutQuotePL;
    }

    public Map<Integer, Double> getPlayerCheckoutQuoteOfLeg(Player player) {
        if(!leg.getWinner().getName().equals(player.getName())) {
            return new HashMap<Integer, Double>() {{put(leg.getLegNumber(), 0.0);}};
        }
        System.out.println(player.getName());
        int chancesToDoubleOut = 0;
        for(Round round : leg.getRounds()) {
            Throw dartThrow = round.getPlayerThrows().get(player);
            int currentScore = getPlayerPointsAtRoundBegin(player, round.getRoundNumber());
            for(Dart dart : dartThrow.getDarts()) {
                if(currentScore<=40&&currentScore%2==0||currentScore==50) {
                    chancesToDoubleOut++;
                }
                currentScore-=dart.getPoints();
            }
        }
        Double checkoutQuote = 1.0 / chancesToDoubleOut;
        return new HashMap<>(){{put(leg.getLegNumber(), checkoutQuote);}};
    }

    private Integer getPlayerPointsAtRoundBegin(Player player, int roundNumber) {
        return leg.getPlayerScoreAtRoundBegin().get(player).get(roundNumber);
    }

    private void fillPlayerScoreAtRoundBeginMap() {
        int startScore = leg.getStartScore();
        for(Player player : leg.getPlayers()) {
            fillForPlayer(startScore, player);
        }
    }

    private void fillForPlayer(int startScore, Player player) {
        int scoreAtBeginOfRound = startScore;
        Map<Integer, Integer> roundScore = leg.getPlayerScoreAtRoundBegin().get(player);
        if(roundScore == null) {
            roundScore = new HashMap<>();
            leg.putPlayerScoreAtRoundBegin(player, roundScore);
        }
        for(Round round : leg.getRounds()) {
            scoreAtBeginOfRound = fillForRound(player, scoreAtBeginOfRound, roundScore, round);
        }
        leg.putPlayerScoreAtRoundBegin(player,roundScore);
    }

    private static int fillForRound(Player player, int scoreAtBeginOfRound, Map<Integer, Integer> roundScore, Round round) {
        Throw dartThrow = round.getPlayerThrows().get(player);
        int scoreToSubtract = 0;
        for(Dart dart : dartThrow.getDarts()) {
            scoreToSubtract += dart.getPoints();
        }
        if(scoreToSubtract> scoreAtBeginOfRound) {
            scoreToSubtract = 0;
        }
        roundScore.put(round.getRoundNumber(), scoreAtBeginOfRound);
        scoreAtBeginOfRound -=scoreToSubtract;
        return scoreAtBeginOfRound;
    }

}

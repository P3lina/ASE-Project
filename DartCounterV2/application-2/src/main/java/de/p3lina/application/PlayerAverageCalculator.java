package de.p3lina.application;

import de.p3lina.domain.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerAverageCalculator {

    public Map<Player, Double> getPlayersAveragesOfLeg(Leg leg) {
        Map<Player, Double> playersAveragePL = new HashMap<>();
        for(Player player : leg.getPlayers()) {
            playersAveragePL.put(player, getPlayerAverageOfLeg(player, leg));
        }
        return playersAveragePL;
    }

    public Double getPlayerAverageOfLeg(Player player, Leg leg) {
        int totalRoundCount = getRoundNumberOfLastRound(leg);
        if(totalRoundCount==0) {
            return 0.0;
        }
        double sumPlayerAveragesPerRound = getSumOfPlayerAveragesOfRounds(leg.getRounds(), player);
        return sumPlayerAveragesPerRound / totalRoundCount;
    }

    private int getRoundNumberOfLastRound(Leg leg) {
        return leg.getRounds().get(
                        leg.getRounds().size()-1)
                .getRoundNumber();
    }

    private Double getSumOfPlayerAveragesOfRounds(List<Round> rounds, Player player) {
        double sumPlayerAveragesPerRound = 0.0;
        for(Round round : rounds) {
            Double playerAverageOfRound = getPlayerAverageOfRound(round, player);
            if(playerAverageOfRound==-1.0) {
                break;
            }
            sumPlayerAveragesPerRound += playerAverageOfRound;
        }
        return sumPlayerAveragesPerRound;
    }

    public Double getPlayerAverageOfRound(Round round, Player player) {
        double playerAveragePerRound;
        int sumPlayerDart = 0;
        Throw dartThrow = round.getPlayerThrows().get(player);
        if(dartThrow.getDarts().size()==0) {
            return -1.0;
        }
        for(Dart dart : dartThrow.getDarts()) {
            sumPlayerDart += dart.getPoints();
        }
        playerAveragePerRound = sumPlayerDart;
        return playerAveragePerRound;
    }


}

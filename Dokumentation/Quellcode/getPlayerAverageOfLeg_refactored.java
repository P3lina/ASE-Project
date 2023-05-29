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
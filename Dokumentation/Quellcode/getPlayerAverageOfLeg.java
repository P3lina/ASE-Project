public Double getPlayerAverageOfLeg(Player player, Leg leg) {
        double playerAveragePL;
        double sumPlayerAveragesPerRound = 0.0;
        int totalRoundCount = (leg.getRounds().get(leg.getRounds().size()-1).getRoundNumber());
        for(Round round : leg.getRounds()) {
            Double playerAverageOfRound = getPlayerAverageOfRound(round, player);
            if(playerAverageOfRound==-1.0) {
                totalRoundCount--;
                continue;
            }
            sumPlayerAveragesPerRound += playerAverageOfRound;
        }
        if(totalRoundCount==0) {
            return 0.0;
        }
        playerAveragePL = sumPlayerAveragesPerRound / totalRoundCount;
        return playerAveragePL;
    }
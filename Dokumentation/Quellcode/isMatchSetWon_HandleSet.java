public IsWon isMatchSetWon(IsSetWonParameter isSetWonParameter) {
        Set set = isSetWonParameter.getSet();
        int legCount = isSetWonParameter.getLegCount();
        int playerCount = isSetWonParameter.getPlayerCount();
        return super.isMatchSetWon(set.getLegs(), Leg::getWinner, legCount, playerCount);
    }
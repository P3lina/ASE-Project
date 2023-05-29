private Leg createLeg(List<Player> players, Round round) {
        Leg leg = new Leg(1, players);
        leg.addRound(round);
        return leg;
    }
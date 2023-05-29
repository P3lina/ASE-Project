package de.p3lina.application;

import de.p3lina.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchMock extends Match {
    public MatchMock() {
        super(new MatchBuilder(1, 1, List.of(),1));
    }
    PlayerMock player1 = new PlayerMock("Player1");
    PlayerMock player2 = new PlayerMock("Player2");

    @Override
    public PlayerMock getWinner() {
        return player1;
    }

    @Override
    public List<Set> getSets() {
        return List.of(createSet());
    }

    private Set createSet() {
        Set set = new Set(1);
        set.addLeg(createLeg());
        return set;
    }

    private LegMock createLeg() {
        LegMock leg = new LegMock(1, List.of(player1, player2));
        leg.setPlayerScore(player1, 42);
        leg.setStatistics(createStatistics());
        leg.addRound(createRound());
        return leg;
    }

    private Round createRound() {
        Round round = new Round(1);
        round.addPlayerThrow(player1, createThrow());
        return round;
    }

    private Throw createThrow() {
        Throw dartThrow = new Throw();
        dartThrow.addDart(new Dart(PossibleDarts.D5));
        dartThrow.addDart(new Dart(PossibleDarts.D10));
        dartThrow.addDart(new Dart(PossibleDarts.D15));
        return dartThrow;
    }

    private Statistics createStatistics() {
        Statistics statistics = new Statistics();
        statistics.setCheckoutQuote(createCheckoutQuote());
        statistics.setAverage(createAverages());
        return statistics;
    }

    private Map<Player, Map<Integer,Double>> createCheckoutQuote() {
        return new HashMap<>(){{put(player1, new HashMap<>(){{put(1,0.0);}});}};
    }

    private Map<Player, Double> createAverages() {
        return new HashMap<>(){{put(player1, 60.0);}};
    }
}

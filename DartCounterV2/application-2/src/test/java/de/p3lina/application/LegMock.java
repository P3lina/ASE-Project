package de.p3lina.application;

import de.p3lina.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegMock extends Leg{

    PlayerMock player1;
    PlayerMock player2;
    Statistics statistics;

    public LegMock(int legNumber, List<Player> players) {
        super(legNumber, players);
        this.player1 = new PlayerMock("Player 1");
        this.player2 = new PlayerMock("Player 2");
        this.setPlayers(List.of(player1, player2));
        this.setPlayerScore(player1, 42);
        this.statistics = new Statistics();
        this.setStatistics(statistics);
    }

    @Override
    public List<Round> getRounds() {
        Round round = new Round(1);
        round.addPlayerThrow(player1, createThrow());
        return List.of(round);
    }

    @Override
    public Map<Player, Double> getPlayerAverages() {
        Map<Player, Double> averages = new HashMap<>();
        averages.put(player1, 60.0);
        return averages;
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    private Throw createThrow() {
        Throw dartThrow = new Throw();
        dartThrow.addDart(new Dart(PossibleDarts.D5));
        dartThrow.addDart(new Dart(PossibleDarts.D10));
        dartThrow.addDart(new Dart(PossibleDarts.D15));
        return dartThrow;
    }
}
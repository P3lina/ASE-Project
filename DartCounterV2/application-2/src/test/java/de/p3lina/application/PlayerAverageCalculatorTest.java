package de.p3lina.application;

import de.p3lina.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerAverageCalculatorTest {

    private PlayerAverageCalculator calculator;
    private List<Player> players;
    private Round round;
    private Leg leg;
    private Map<Player, Throw> playerThrows;
    private Map<Player, Double> expectedAverages;

    @BeforeEach
    public void setup() {
        calculator = new PlayerAverageCalculator();

        players = Arrays.asList(new Player("Player1"), new Player("Player2"));
        playerThrows = new HashMap<>();
        expectedAverages = new HashMap<>();

        playerThrows.put(players.get(0), createThrow(PossibleDarts.S20, PossibleDarts.S15, PossibleDarts.S5));
        playerThrows.put(players.get(1), createThrow(PossibleDarts.T20, PossibleDarts.T19, PossibleDarts.T18));

        expectedAverages.put(players.get(0), 40.0);
        expectedAverages.put(players.get(1), 171.0);

        round = createRound(playerThrows);
        leg = createLeg(players, round);
    }

    private Throw createThrow(PossibleDarts... darts) {
        Throw aThrow = new Throw();
        for (PossibleDarts dart : darts) {
            aThrow.addDart(new Dart(dart));
        }
        return aThrow;
    }

    private Round createRound(Map<Player, Throw> playerThrows) {
        Round round = new Round(1);
        playerThrows.forEach(round::addPlayerThrow);
        return round;
    }

    private Leg createLeg(List<Player> players, Round round) {
        Leg leg = new Leg(1, players);
        leg.addRound(round);
        return leg;
    }

    @Test
    public void testPlayerAverages() {
        players.forEach(this::assertPlayerAverageOfRound);
        players.forEach(this::assertPlayerAverageOfLeg);
        assertPlayersAveragesOfLeg();
    }

    private void assertPlayerAverageOfRound(Player player) {
        double expectedAverage = expectedAverages.get(player);
        double actualAverage = calculator.getPlayerAverageOfRound(round, player);
        assertEquals(expectedAverage, actualAverage, "Unexpected average for " + player.getName());
    }

    private void assertPlayerAverageOfLeg(Player player) {
        double expectedAverage = expectedAverages.get(player);
        double actualAverage = calculator.getPlayerAverageOfLeg(player, leg);
        assertEquals(expectedAverage, actualAverage, "Unexpected average for " + player.getName());
    }

    private void assertPlayersAveragesOfLeg() {
        Map<Player, Double> averages = calculator.getPlayersAveragesOfLeg(leg);
        players.forEach(player -> {
            assertTrue(averages.containsKey(player), "Averages should contain " + player.getName());
            assertEquals(expectedAverages.get(player), averages.get(player), "Unexpected average for " + player.getName());
        });
    }
}

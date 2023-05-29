package de.p3lina.application;

import de.p3lina.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCheckoutQuoteCalculatorTest {

    private PlayerCheckoutQuoteCalculator calculator;
    private Player player1;
    private Player player2;
    private Round round;
    private Leg leg;

    @BeforeEach
    public void setup() {
        player1 = new Player("Player1");
        player2 = new Player("Player2");

        Dart dart1 = new Dart(PossibleDarts.S20);
        Dart dart2 = new Dart(PossibleDarts.S10);
        Dart dart3 = new Dart(PossibleDarts.D5);
        Dart dart4 = new Dart(PossibleDarts.T5);
        Dart dart5 = new Dart(PossibleDarts.T3);
        Dart dart6 = new Dart(PossibleDarts.D5);

        Throw throw1 = new Throw();
        Throw throw2 = new Throw();
        throw1.addDart(dart1);
        throw1.addDart(dart2);
        throw1.addDart(dart3);
        throw2.addDart(dart4);
        throw2.addDart(dart5);
        throw2.addDart(dart6);

        round = new Round(1);
        round.addPlayerThrow(player1, throw1);
        round.addPlayerThrow(player2, throw2);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        leg = new Leg(1, players);
        leg.addRound(round);
        leg.setWinner(player1); // assuming player1 is the winner
        leg.setStartScore(40); // assuming a start score of 40

        calculator = new PlayerCheckoutQuoteCalculator(leg);
    }

    @Test
    public void getPlayerCheckoutQuoteOfLegTest() {
        Map<Integer, Double> checkoutQuote = calculator.getPlayerCheckoutQuoteOfLeg(player1);
        assertTrue(checkoutQuote.containsKey(leg.getLegNumber()), "Checkout quote should contain leg number");
        assertEquals((double)1/3, checkoutQuote.get(leg.getLegNumber()), "Checkout quote for the leg should be 1 for the winner");
    }

    @Test
    public void getPlayersCheckoutQuoteOfLegTest() {
        Map<Player, Map<Integer, Double>> checkoutQuotes = calculator.getPlayersCheckoutQuoteOfLeg();
        assertTrue(checkoutQuotes.containsKey(player1), "Checkout quotes should contain player1");
        assertTrue(checkoutQuotes.containsKey(player2), "Checkout quotes should contain player2");
        assertEquals((double)1/3, checkoutQuotes.get(player1).get(leg.getLegNumber()), "Checkout quote for player1 should be 1");
        assertEquals(0.0, checkoutQuotes.get(player2).get(leg.getLegNumber()), "Checkout quote for player2 should be 0");
    }
}
package de.p3lina.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchHistoryTest {

    MatchMock match = new MatchMock();
    MatchHistory history = new MatchHistory();

    @Test
    public void getMatchHistoryString() {
        String historyString = history.getMatchHistoryString(match);
        String correctString = "Match History:\n" +
                "\tSet No. 1:\n" +
                "\t\tLeg No. 1:\n" +
                "\t\t\tRound No. 1:\n" +
                "\t\t\t\tPlayer 1: D5,D10,D15\n" +
                "\t\t\tAverages:\n" +
                "\t\t\t\tPlayer 1: 60.0\n" +
                "\t-> Player 1 won the match!";
        assertEquals(correctString, historyString);
    }
}

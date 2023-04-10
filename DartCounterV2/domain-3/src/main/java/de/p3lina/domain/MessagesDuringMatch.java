package de.p3lina.domain;

public interface MessagesDuringMatch {

    void printCurrentSetNumber();
    void printCurrentLegNumber();
    void printStatistics();

    void printRemainingScore(int remainingScore);

    void printWhoseTurnItIs(String playerName);

    void printPlayerInputDart(String playerName);

    void printThrow(String name, int points);
    void printPlayerBusted(String name, int resetPointsTo);
    void printPlayerCheckedOut(String name);
}

package de.p3lina.domain;

public interface MessagesDuringMatch {

    void printCurrentSetNumber();
    void printCurrentLegNumber();
    void printStatistics();

    void printRemainingScore(int remainingScore);

    void printWhoseTurnItIs(String playerName);

    void printPlayerInputDart(String playerName);
}

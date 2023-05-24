package de.p3lina.domain.messages;

public interface ThrowResultMessages {
    void printPlayerBusted(String name, int resetPointsTo);
    void printPlayerCheckedOut(String name);
    void printRemainingScore(int remainingScore);
}

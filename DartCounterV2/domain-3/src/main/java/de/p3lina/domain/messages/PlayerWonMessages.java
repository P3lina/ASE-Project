package de.p3lina.domain.messages;

public interface PlayerWonMessages {
    void printPlayerWonSet(String name, int setNumber);
    void printPlayerWonMatch(String name);
    void printPlayerWonLeg(String name, int legNumber);
}

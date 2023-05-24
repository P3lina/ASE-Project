package de.p3lina.domain.messages;


public interface MessagesDuringMatch extends PlayerPromptMessages, PlayerWonMessages, StatisticsMessages, ThrowResultMessages {

    void printCurrentSetNumber(int setNumber);
    void printCurrentLegNumber(int legNumber);









}

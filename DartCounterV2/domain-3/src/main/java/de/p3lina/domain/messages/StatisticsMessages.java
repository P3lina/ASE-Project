package de.p3lina.domain.messages;

public interface StatisticsMessages {
    void printPlayerAverages(String name, Double average);
    void printPlayerRoundAverage(String name, Double average, Double playerLegAverage);
    void printPlayerCheckoutQuote(String name, int legNumber, Double playerCheckoutQuote);
}

package de.p3lina.domain;

import java.util.Map;

public class Statistics {

    private Map<Player, Map<Integer, Double>> checkoutQuote;
    private Map<Player, Double> average;

    public Map<Player, Map<Integer, Double>> getCheckoutQuote() {
        return checkoutQuote;
    }

    public void setCheckoutQuote(Map<Player, Map<Integer, Double>> checkoutQuote) {
        this.checkoutQuote = checkoutQuote;
    }

    public Map<Player, Double> getAverage() {
        return average;
    }

    public void setAverage(Map<Player, Double> average) {
        this.average = average;
    }
}

package de.p3lina.domain;



public class Dart {

    public Dart(PossibleDarts dart) {
        this.dart = dart;
        this.points = dart.points;
        this.doubleNumber = dart.isDouble;
    }

    private PossibleDarts dart;
    private int points;
    private boolean doubleNumber;

    public int getPoints() {
        return points;
    }

    public boolean isDoubleNumber() {
        return doubleNumber;
    }
}
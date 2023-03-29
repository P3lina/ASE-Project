package de.p3lina.domain;

import lombok.Getter;


public class Dart {

    public Dart(PossibleDarts dart) {
        this.dart = dart;
        this.points = dart.points;
        this.doubleNumber = dart.isDouble;
    }

    private PossibleDarts dart;
    @Getter
    private int points;
    @Getter
    private boolean doubleNumber;

}
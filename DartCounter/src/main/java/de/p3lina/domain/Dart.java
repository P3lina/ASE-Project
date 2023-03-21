package de.p3lina.domain;

import lombok.Getter;


public class Dart {

    public Dart(PossibleDarts dart) {
        this.dart = dart;
        this.points = dart.points;
    }

    private PossibleDarts dart;
    @Getter
    private int points;

}
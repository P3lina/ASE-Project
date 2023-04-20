package de.p3lina.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Throw {
    @Getter
    @Setter
    private List<Dart> darts;
    @Getter
    @Setter
    private boolean checkedOut;

    public Throw() {
        this.darts = new ArrayList<>();
    }

    public void addDart(Dart dart) {
        this.darts.add(dart);
    }

}
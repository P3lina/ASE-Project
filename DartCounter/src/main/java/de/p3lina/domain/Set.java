package de.p3lina.domain;


import lombok.Getter;

import java.util.List;

public class Set {


    @Getter
    private List<Leg> legs;

    public void addLeg(Leg leg) {
        legs.add(leg);
    }
}

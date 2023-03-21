package de.p3lina.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Set {


    @Getter
    @Setter
    private List<Leg> legs;

    public void addLeg(Leg leg) {
        legs.add(leg);
    }
}

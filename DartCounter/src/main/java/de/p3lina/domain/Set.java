package de.p3lina.domain;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@RequiredArgsConstructor
public class Set {


    @Getter
    @Setter
    private List<Leg> legs;
    @Getter
    @Setter
    private Player winner;
    @Getter
    @NonNull
    private int setNumber;

    public void addLeg(Leg leg) {
        legs.add(leg);
    }
}

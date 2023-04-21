package de.p3lina.domain;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
public class Set {


    @Getter
    @Setter
    private List<Leg> legs;
    @Getter
    @Setter
    private Player winner;
    @Getter
    private int setNumber;

    public Set(int setNumber) {
        this.setNumber = setNumber;
        this.legs = new ArrayList<>();
    }

    public void addLeg(Leg leg) {
        legs.add(leg);
    }

    public Leg getLegByIndex(int legIndex) {
        for(Leg leg : legs) {
            if(leg.getLegNumber() == legIndex) {
                return leg;
            }
        }
        return null;
    }

}

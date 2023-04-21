package de.p3lina.domain;


import java.util.ArrayList;
import java.util.List;
public class Set {


    private List<Leg> legs;
    private Player winner;
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

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getSetNumber() {
        return setNumber;
    }
}

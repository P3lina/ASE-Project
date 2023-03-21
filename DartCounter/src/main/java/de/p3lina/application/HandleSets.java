package de.p3lina.application;

import de.p3lina.domain.Leg;
import de.p3lina.domain.Player;
import de.p3lina.domain.Set;

import java.util.List;

public class HandleSets {

    public static void startSets(List<Set> sets, int legCount, List<Player> players, int startScore){
        sets = initializeLegs(sets, legCount);
        startLegs(sets, players, startScore);
    }

    private static List<Set> initializeLegs(List<Set> sets, int legCount){
        for(Set set : sets) {
            for (int indexOfLeg = 0; indexOfLeg < legCount; indexOfLeg++) {
                Leg leg = new Leg();
                set.addLeg(leg);
            }
        }
        return sets;
    }

    private static void startLegs(List<Set> sets, List<Player> players, int startScore) {
        for(Set set : sets) {
            HandleLegs.startLegs(set.getLegs(), players, startScore);
        }
    }

}

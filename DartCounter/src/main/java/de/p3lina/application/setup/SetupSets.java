package de.p3lina.application.setup;

import de.p3lina.domain.Leg;
import de.p3lina.domain.Player;
import de.p3lina.domain.Set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetupSets {

    public void initializeSets(List<Set> sets, int legCount, List<Player> players, int startScore){
        sets = initializeLegsOfSets(sets, legCount);
        initializeLegs(sets, players, startScore);
    }

    private List<Set> initializeLegsOfSets(List<Set> sets, int legCount){
        List<Set> returnSet = new ArrayList<Set>(sets);
        for(Set set : sets) {
            returnSet.add(initializeLegsOfSet(set, legCount));
        }
        return returnSet;
    }

    private Set initializeLegsOfSet(Set set, int legCount) {
        set.setLegs(new ArrayList<Leg>());
        for (int indexOfLeg = 0; indexOfLeg < legCount; indexOfLeg++) {
            Leg leg = new Leg(new HashMap<Player, Integer>(), indexOfLeg+1);
            set.addLeg(leg);
        }
        return set;
    }

    private void initializeLegs(List<Set> sets, List<Player> players, int startScore) {
        for(Set set : sets) {
            new SetupLegs().initializeLegs(set.getLegs(), players, startScore);
        }
    }

}
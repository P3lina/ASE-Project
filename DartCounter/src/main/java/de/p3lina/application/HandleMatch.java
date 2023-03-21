package de.p3lina.application;

import de.p3lina.domain.Leg;
import de.p3lina.domain.Match;
import de.p3lina.domain.Player;
import de.p3lina.domain.Set;

import java.util.ArrayList;
import java.util.List;

public class HandleMatch {

    //Only one match can exist at a time
    private Match match;

    public void initializeMatch(int legCount, int setCount, List<Player> players, int startScore) {
        match = new Match(new ArrayList<Set>(), legCount, setCount, players, startScore);
        initializeSets(setCount);
        new HandleSets().initializeSets(match.getSets(), legCount, players, startScore);
        for(Set set : match.getSets()) {
            for(Leg leg : set.getLegs()) {
                for(Player player : match.getPlayers()) {
                    System.out.println(leg.getPlayerScore().get(player));
                }
            }
        }
    }

    //Eventuell kann man das Initialisieren von Sets und Legs auch Zusammenfassen, also das Initialisieren und starten jeweils
    private void initializeSets(int setCount) {
        for (int indexOfSet = 0; indexOfSet < setCount; indexOfSet++) {
            List<Set> sets = match.getSets();
            sets.add(new Set());
            match.setSets(sets);
        }
    }


}
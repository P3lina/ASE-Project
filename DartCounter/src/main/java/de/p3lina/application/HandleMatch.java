package de.p3lina.application;

import de.p3lina.domain.Match;
import de.p3lina.domain.Player;
import de.p3lina.domain.Set;

import java.util.List;

public class HandleMatch {

    private static Match match;

    public static void initializeMatch(int legCount, int setCount, List<Player> players, int startScore) {
        match = new Match(legCount, setCount, players, startScore);
        startMatch(setCount, legCount, players, startScore);
    }

    private static void startMatch(int setCount, int legCount, List<Player> players, int startScore) {
        initializeSets(setCount);
        startSets(legCount, players, startScore);
    }

    //Eventuell kann man das Initialisieren von Sets und Legs auch Zusammenfassen, also das Initialisieren und starten jeweils
    private static void initializeSets(int setCount) {
        for (int indexOfSet = 0; indexOfSet < setCount; indexOfSet++) {
            List<Set> sets = match.getSets();
            sets.add(new Set());
            match.setSets(sets);
        }
    }

    private static void startSets(int legCount,List<Player> players, int startScore) {
        HandleSets.startSets(match.getSets(), legCount, players, startScore);
    }
}
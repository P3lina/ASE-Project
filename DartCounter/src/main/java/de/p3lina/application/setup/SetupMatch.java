package de.p3lina.application.setup;

import de.p3lina.domain.*;

import java.util.ArrayList;
import java.util.List;

public class SetupMatch {

    private Match initializedMatch;

    public Match initializeAndReturnMatch(MatchInfos matchInfos) {
        initializedMatch = new Match(
                new ArrayList<Set>(),
                matchInfos.getLegCount(),
                matchInfos.getSetCount(),
                matchInfos.getPlayers(),
                matchInfos.getStartScore());
        initializeSets(matchInfos.getSetCount());
        new SetupSets().initializeSets(
                initializedMatch.getSets(),
                matchInfos.getLegCount(),
                matchInfos.getPlayers(),
                matchInfos.getStartScore());
        return initializedMatch;
    }

    //Eventuell kann man das Initialisieren von Sets und Legs auch Zusammenfassen, also das Initialisieren und starten jeweils
    private void initializeSets(int setCount) {
        for (int indexOfSet = 0; indexOfSet < setCount; indexOfSet++) {
            List<Set> sets = initializedMatch.getSets();
            sets.add(new Set(indexOfSet+1));
            initializedMatch.setSets(sets);
        }
    }


}
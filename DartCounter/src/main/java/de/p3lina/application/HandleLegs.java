package de.p3lina.application;

import de.p3lina.domain.Leg;
import de.p3lina.domain.Player;

import java.util.List;

public class HandleLegs {



    public static void startLegs(List<Leg> legs, List<Player> players, int startScore) {
        initializePlayerScores(legs, players, startScore);
    }

    private static void initializePlayerScores(List<Leg> legs, List<Player> players, int startScore) {
        for(Leg leg : legs) {
            for(Player player : players){
                leg.addPlayerWithScore(player, startScore);
            }
        }
    }
}

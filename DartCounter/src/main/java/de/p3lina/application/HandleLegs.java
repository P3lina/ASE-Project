package de.p3lina.application;

import de.p3lina.domain.Leg;
import de.p3lina.domain.Player;

import java.util.List;

public class HandleLegs {



    public void initializeLegs(List<Leg> legs, List<Player> players, int startScore) {
        initializePlayerScoresForLegs(legs, players, startScore);
    }

    private void initializePlayerScoresForLegs(List<Leg> legs, List<Player> players, int startScore) {
        for(Leg leg : legs) {
            for(Player player : players){
                leg.addPlayerWithScore(player, startScore);
            }
        }
    }
}

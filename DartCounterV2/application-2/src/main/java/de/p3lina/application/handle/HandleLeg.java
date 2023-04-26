package de.p3lina.application.handle;

import de.p3lina.application.PlayerAverageCalculator;
import de.p3lina.domain.*;

import java.util.*;

public class HandleLeg {

    private MessagesDuringMatch message;
    public HandleLeg(MessagesDuringMatch message) {
        this.message = message;
    }

    public Leg processLeg(List<Player> players, int legNumber, int startScore) {
        Leg leg = new Leg(legNumber, players);
        initPlayerScore(leg, players, startScore);
        message.printCurrentLegNumber(leg.getLegNumber());
        int roundNumber = 0;
        while (leg.getWinner() == null) {
            HandleRound roundHandle = new HandleRound(players, leg, message);
            Round round = new Round(roundNumber);
            leg.addRound(round);
            roundHandle.processRound(round);
            roundNumber++;
        }
        PlayerAverageCalculator averageCalculator = new PlayerAverageCalculator();
        leg.setPlayerAverages(averageCalculator.getPlayersAveragesOfLeg(leg));
        leg.setPlayers(getPlayerOrderForNextLeg(leg));
        message.printPlayerWonLeg(leg.getWinner().getName(), leg.getLegNumber());
        printPlayerAverages(leg.getPlayerAverages());
        return leg;
    }

    private void printPlayerAverages(Map<Player, Double> playerAverages) {
        for(Player player : playerAverages.keySet()) {
            Double average = playerAverages.get(player);
            message.printPlayerAverages(player.getName(), average);
        }
    }

    private void initPlayerScore(Leg leg, List<Player> players, int startScore) {
        for(Player player : players) {
            leg.setPlayerScore(player, startScore);
        }
    }

    private List<Player> getPlayerOrderForNextLeg(Leg leg) {
        List<Map.Entry<Player, Integer>> list = new ArrayList<>(leg.getPlayerScore().entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        List<Player> players = new ArrayList<>();
        for(Map.Entry<Player, Integer> entry : list) {
            players.add(entry.getKey());
        }
        return players;
    }


}

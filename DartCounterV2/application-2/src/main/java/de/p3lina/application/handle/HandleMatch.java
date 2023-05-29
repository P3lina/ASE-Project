package de.p3lina.application.handle;

import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

import java.util.List;

public class HandleMatch extends HandleGame<Set> implements Handle<Match, MatchBuilder, IsWon, Match>{


    MessagesDuringMatch message;
    public HandleMatch(MessagesDuringMatch message) {
        this.message = message;
    }

    public Match process(MatchBuilder matchBuilder) {
        Match match = new Match.MatchBuilder(matchBuilder.getLegCount(), matchBuilder.getSetCount(),matchBuilder.getPlayers(),matchBuilder.getStartScore()).build();
        List<Player> players = match.getPlayers();
        int setNumber = 1;
        while(match.getWinner() == null) {
            HandleSet setHandle = new HandleSet(message);
            Set set = setHandle.process(new HandleSetProcessParams(players, matchBuilder.getStartScore(), setNumber, match.getLegCount()));
            //update player order
            players = set.getLegs().get(set.getLegs().size()-1).getPlayers();
            setNumber++;
            match.addSet(set);
            //check if match is won
            if(isMatchSetWon(match).isWon()) {
                match.setWinner(isMatchSetWon(match).getPlayer());
            }
        }
        message.printPlayerWonMatch(match.getWinner().getName());
        return match;
    }


    public IsWon isMatchSetWon(Match match) {
        int setCount = match.getSetCount();
        int playerCount = match.getPlayers().size();
        return super.isMatchSetWon(match.getSets(), Set::getWinner, setCount, playerCount);
    }
}
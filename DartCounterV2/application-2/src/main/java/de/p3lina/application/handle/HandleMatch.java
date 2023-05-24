package de.p3lina.application.handle;

import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMatch extends HandleGame<Set> implements Handle<Match, MatchInfos, IsWon, Match>{


    MessagesDuringMatch message;
    public HandleMatch(MessagesDuringMatch message) {
        this.message = message;
    }

    public Match process(MatchInfos matchInfos) {
        Match match = new Match(matchInfos);
        List<Player> players = match.getPlayers();
        int setNumber = 1;
        while(match.getWinner() == null) {
            HandleSet setHandle = new HandleSet(message);
            Set set = setHandle.process(new HandleSetProcessParams(players, matchInfos.getStartScore(), setNumber, match.getLegCount()));
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
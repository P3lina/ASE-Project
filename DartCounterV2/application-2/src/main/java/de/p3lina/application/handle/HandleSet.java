package de.p3lina.application.handle;

import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleSet extends HandleGame<Leg> implements Handle<Set, HandleSetProcessParams, IsWon, IsSetWonParameter>  {


    private MessagesDuringMatch message;

    public HandleSet(MessagesDuringMatch message) {
        this.message = message;
    }

    public Set process(HandleSetProcessParams params) {
        List<Player> players = params.getPlayers();
        int startScore = params.getStartScore();
        int setNumber = params.getSetNumber();
        int legCount = params.getLegCount();
        message.printCurrentSetNumber(setNumber);
        Set set = new Set(setNumber);
        int legNumber = 1;
        while (set.getWinner() == null) {
            HandleLeg legHandle = new HandleLeg(message);
            Leg leg = legHandle.process(new HandleLegProcessParams(players, legNumber, startScore));
            set.addLeg(leg);
            //updated player order
            players = leg.getPlayers();
            if(isMatchSetWon(new IsSetWonParameter(set, players.size(), legCount)).isWon()) {
                set.setWinner(isMatchSetWon(new IsSetWonParameter(set, players.size(), legCount)).getPlayer());
                break;
            }
            legNumber++;
        }
        message.printPlayerWonSet(set.getWinner().getName(), set.getSetNumber());
        return set;
    }



    public IsWon isMatchSetWon(IsSetWonParameter isSetWonParameter) {
        Set set = isSetWonParameter.getSet();
        int legCount = isSetWonParameter.getLegCount();
        int playerCount = isSetWonParameter.getPlayerCount();
        return super.isMatchSetWon(set.getLegs(), Leg::getWinner, legCount, playerCount);
    }

}

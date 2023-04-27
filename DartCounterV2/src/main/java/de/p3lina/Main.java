package de.p3lina;

import de.p3lina.adapters.MatchMessages;
import de.p3lina.adapters.MessagesOutsideMatch;
import de.p3lina.adapters.SetupMatchQuestions;
import de.p3lina.application.JSON;
import de.p3lina.application.MatchHistory;
import de.p3lina.application.handle.HandleMatch;
import de.p3lina.domain.Match;
import de.p3lina.domain.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main( String[] args ) {
        //Dart dart = new Dart(PossibleDarts.valueOf("Thrown dart, e.g. T20"));
        List<Player> players = new ArrayList<Player>();
        Player p = null;
        for(int i=0;i<3;i++){
            p = new Player("Spieler " + i);
            players.add(p);
        }
        MatchMessages messages = new MatchMessages();
        HandleMatch matchHandle = new HandleMatch(messages);
        Match match = matchHandle.proceedMatch(new SetupMatchQuestions().getMatchInfos());
        MessagesOutsideMatch messagesOutsideMatch = new MessagesOutsideMatch();
        MatchHistory matchHistory = new MatchHistory();
        matchHistory.saveMatchHistory(match, messagesOutsideMatch);
    }
}

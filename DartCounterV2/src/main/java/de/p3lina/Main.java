package de.p3lina;

import de.p3lina.adapters.MatchMessages;
import de.p3lina.adapters.MessagesOutsideMatch;
import de.p3lina.adapters.SetupMatchQuestions;
import de.p3lina.application.MatchHistory;
import de.p3lina.application.handle.HandleMatch;
import de.p3lina.domain.Match;

public class Main {
    public static void main( String[] args ) {
        MatchMessages messages = new MatchMessages();
        HandleMatch matchHandle = new HandleMatch(messages);
        Match match = matchHandle.proceedMatch(new SetupMatchQuestions().getMatchInfos());
        MessagesOutsideMatch messagesOutsideMatch = new MessagesOutsideMatch();
        MatchHistory matchHistory = new MatchHistory();
        matchHistory.saveMatchHistory(match, messagesOutsideMatch);
    }
}

package de.p3lina.adapters;



import de.p3lina.application.UserCommunicationService;
import de.p3lina.adapters.i18n.I18n;
import de.p3lina.domain.MatchBuilder;
import de.p3lina.domain.Player;
import de.p3lina.domain.i18n.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SetupMatchQuestions {

    private String lastQuestion;
    private UserCommunicationService userCommunicationService;

    public SetupMatchQuestions() {
        this.userCommunicationService = new UserCommunicationService();
    }

    public MatchBuilder getMatchInfos() {
        printMessageSlowlyToConsole(I18n.getMessage(Messages.WELCOME_MESSAGE));
        printMessageSlowlyToConsole(I18n.getMessage(Messages.HOW_MANY_PLAYERS));
        int playerCount = checkUserInput(userCommunicationService.getUserInput().toInt());
        List<Player> players = getPlayersFromUserInput(playerCount);
        printMessageSlowlyToConsole(I18n.getMessage(Messages.START_SCORE));
        int gameMode = checkUserInput(userCommunicationService.getUserInput().toInt());
        printMessageSlowlyToConsole(I18n.getMessage(Messages.HOW_MANY_SETS));
        int setCount = getValidSetCountFromUser(playerCount);
        printMessageSlowlyToConsole(I18n.getMessage(Messages.HOW_MANY_LEGS));
        int legCount = getValidLegCountFromUser(playerCount);
        return new MatchBuilder(playerCount, players, gameMode, setCount, legCount);
    }


    private int getValidSetCountFromUser(int playerCount) {
        int userInput = userCommunicationService.getUserInput().toInt();
        if(userInput==1) {
            return 1;
        }
        if(userInput%playerCount==0) {
            printMessageSlowlyToConsole(I18n.getMessage(Messages.INVALID_SET_COUNT));
            return getValidSetCountFromUser(playerCount);
        }
        return userInput;
    }

    private int getValidLegCountFromUser(int playerCount) {
        int userInput = userCommunicationService.getUserInput().toInt();
        if(userInput==1) {
            return 1;
        }
        if(userInput%playerCount==0) {
            printMessageSlowlyToConsole(I18n.getMessage(Messages.INVALID_LEG_COUNT));
            return getValidLegCountFromUser(playerCount);
        }
        return userInput;
    }

    private int checkUserInput(int input) {
        if(input==-1) {
            printMessageSlowlyToConsole(lastQuestion);
            return checkUserInput(userCommunicationService.getUserInput().toInt());
        }
        return input;
    }

    private void printMessageSlowlyToConsole(String message) {
        lastQuestion = message;
        for(int indexInMessage = 0; indexInMessage<message.length(); indexInMessage++) {
            char charAtIndex = message.charAt(indexInMessage);
            System.out.print(charAtIndex);
            try{
                TimeUnit.MILLISECONDS.sleep(1);
            }catch(InterruptedException exc) {

            }
        }
        System.out.print("\n");
    }

    private List<Player> getPlayersFromUserInput(int playerCount) {
        List<Player> players = new ArrayList<>();
        while(players.size()<playerCount) {
            printMessageSlowlyToConsole(I18n.getMessage(Messages.ENTER_PLAYER_NAME));
            Player player = new Player(userCommunicationService.getUserInput().toString());
            players.add(player);
        }
        return players;
    }

}

package de.p3lina.adapters;



import de.p3lina.application.UserCommunicationService;
import de.p3lina.adapters.i18n.I18n;
import de.p3lina.domain.MatchInfos;
import de.p3lina.domain.Player;
import de.p3lina.domain.i18n.Messages;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class SetupMatchQuestions {

    private String lastQuestion;
    private UserCommunicationService userCommunicationService;

    public SetupMatchQuestions() {
        this.userCommunicationService = new UserCommunicationService();
    }

    public MatchInfos getMatchInfos() {
        printMessageSlowlyToConsole(I18n.getMessage(Messages.WELCOME_MESSAGE));
        printMessageSlowlyToConsole(I18n.getMessage(Messages.HOW_MANY_PLAYERS));
        int playerCount = checkUserInput(userCommunicationService.getUserInput().toInt());
        List<Player> players = getPlayersFromUserInput(playerCount);
        printMessageSlowlyToConsole(I18n.getMessage(Messages.START_SCORE));
        int gameMode = checkUserInput(userCommunicationService.getUserInput().toInt());
        printMessageSlowlyToConsole(I18n.getMessage(Messages.HOW_MANY_SETS));
        int setCount = getValidSetCountFromUser();
        printMessageSlowlyToConsole(I18n.getMessage(Messages.HOW_MANY_LEGS));
        int legCount = getValidLegCountFromUser();
        return new MatchInfos(playerCount, players, gameMode, setCount, legCount);
    }


    private int getValidSetCountFromUser() {
        int userInput = userCommunicationService.getUserInput().toInt();
        if(userInput%2==0) {
            printMessageSlowlyToConsole(I18n.getMessage(Messages.INVALID_SET_COUNT));
            return getValidSetCountFromUser();
        }
        return userInput;
    }

    private int getValidLegCountFromUser() {
        int userInput = userCommunicationService.getUserInput().toInt();
        if(userInput%2==0) {
            printMessageSlowlyToConsole(I18n.getMessage(Messages.INVALID_LEG_COUNT));
            return getValidLegCountFromUser();
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

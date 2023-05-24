package de.p3lina.adapters;

import de.p3lina.adapters.i18n.I18n;
import de.p3lina.application.UserCommunicationService;
import de.p3lina.domain.i18n.Messages;

public class MessagesOutsideMatch implements de.p3lina.domain.messages.MessagesOutsideMatch {

    UserCommunicationService userCommunicationService;
    public MessagesOutsideMatch() {
        userCommunicationService = new UserCommunicationService();
    }
    @Override
    public boolean askForSaveHistory() {
        System.out.println(I18n.getMessage(Messages.ASK_FOR_SAVE_HISTORY));
        String userInput = userCommunicationService.getUserInput().toString();
        return userInput.equalsIgnoreCase("y");
    }
}

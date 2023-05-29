package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.user.UserInput;
import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

public class HandleDart implements Handle<Dart, Player, Object, Object>{

    private MessagesDuringMatch message;

    public HandleDart(MessagesDuringMatch message) {
        this.message = message;
    }

    public Dart process(Player player) {
        message.printPlayerInputDart(player.getName());
        UserInput userInput = null;
        while(userInput==null||!userInput.isValidDart(userInput)) {
            userInput = UserInput.prepareUserDartInput(new UserCommunicationService().getUserInput().toString());
        }
        if(!userInput.isValidDart(userInput)) {
            return this.process(player);
        }
        PossibleDarts parsedInput = PossibleDarts.valueOf(userInput.toString());
        return new Dart(parsedInput);
    }

    @Override
    public Object isMatchSetWon(Object something) {
        return null;
    }

    public DartStatus getDartStatus(Dart dart, int currentPlayerScore) {
        if(playerBusted(dart, currentPlayerScore)) {
            return DartStatus.BUSTED;
        }
        if(playerCheckedOut(dart, currentPlayerScore)) {
            return DartStatus.CHECKOUT;
        }
        return DartStatus.NOTHING;
    }

    private boolean playerBusted(Dart dart, int currentPlayerScore) {
        if(dart.getPoints()>currentPlayerScore) {
            return true;
        }
        if(currentPlayerScore - dart.getPoints() == 1) {
            return true;
        }
        return currentPlayerScore - dart.getPoints() == 0 && !dart.isDoubleNumber();
    }

    private boolean playerCheckedOut(Dart dart, int currentPlayerScore) {
        return dart.getPoints() == currentPlayerScore && dart.isDoubleNumber();
    }

}

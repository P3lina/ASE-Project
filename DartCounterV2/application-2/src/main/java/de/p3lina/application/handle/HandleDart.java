package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

public class HandleDart implements Handle<Dart, Player, Object, Object>{

    private Player player;
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
        Dart dart = new Dart(parsedInput);
        return dart;
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
        if(currentPlayerScore - dart.getPoints() == 0 && !dart.isDoubleNumber()) {
            return true;
        }
        return false;
    }

    private boolean playerCheckedOut(Dart dart, int currentPlayerScore) {
        if(dart.getPoints()==currentPlayerScore && dart.isDoubleNumber()) {
            return true;
        }
        return false;
    }

}

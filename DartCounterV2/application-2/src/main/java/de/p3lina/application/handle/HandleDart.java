package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
import de.p3lina.domain.*;

public class HandleDart {

    private Player player;
    private MessagesDuringMatch message;

    public HandleDart(Player player, MessagesDuringMatch message) {
        this.player = player;
        this.message = message;
    }

    public Dart processDart() {
        message.printPlayerInputDart(player.getName());
        UserInput userInput = UserInput.prepareUserDartInput(new UserCommunicationService().getUserInput().toString());
        if(!userInput.isValidDart(userInput)) {
            return this.processDart();
        }
        PossibleDarts parsedInput = PossibleDarts.valueOf(userInput.toString());
        Dart dart = new Dart(parsedInput);
        return dart;
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

package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
import de.p3lina.domain.*;

public class HandleDart {

    private Player player;
    private MessagesDuringMatch message;
    private int currentPlayerScore;
    private Leg currentLeg;
    private int playerScoreBeforeThrow;

    public HandleDart(Player player, MessagesDuringMatch message, int playerScoreBeforeThrow, Leg currentLeg) {
        this.player = player;
        this.currentLeg = currentLeg;
        this.currentPlayerScore = this.currentLeg.getPlayerScore().get(this.player);
        this.message = message;
        this.playerScoreBeforeThrow = playerScoreBeforeThrow;
    }

    public DartStatus processDart() {
        message.printPlayerInputDart(player.getName());
        UserInput userInput = UserInput.prepareUserDartInput(new UserCommunicationService().getUserInput().toString());
        if(!userInput.isValidDart(userInput)) {
            return this.processDart();
        }
        PossibleDarts parsedInput = PossibleDarts.valueOf(userInput.toString());
        Dart dart = new Dart(parsedInput);
        message.printThrow(player.getName(), dart.getPoints());
        if(playerBusted(dart)) {
            message.printPlayerBusted(player.getName(), playerScoreBeforeThrow);
            return DartStatus.BUSTED;
        }
        if(playerCheckedOut(dart)) {
            message.printPlayerCheckedOut(player.getName());
            return DartStatus.CHECKOUT;
        }
        this.currentLeg.subtractPlayerScore(player, dart.getPoints());
        message.printRemainingScore(currentPlayerScore);
        return DartStatus.NOTHING;
    }

    private boolean playerBusted(Dart dart) {
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

    private boolean playerCheckedOut(Dart dart) {
        if(dart.getPoints()==currentPlayerScore && dart.isDoubleNumber()) {
            return true;
        }
        return false;
    }

}

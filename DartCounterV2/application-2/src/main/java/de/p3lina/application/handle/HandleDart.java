package de.p3lina.application.handle;

import de.p3lina.application.UserCommunicationService;
import de.p3lina.application.UserInput;
import de.p3lina.domain.Dart;
import de.p3lina.domain.DartStatus;
import de.p3lina.domain.Player;
import de.p3lina.domain.PossibleDarts;

public class HandleDart {

    private Player player;
    private int pointsBeforeThrow;

    public HandleDart(Player player, int pointsBeforeThrow) {

    }

    private DartStatus processDart(Player player, int playerScoreBeforeThrow) {
        message.printPlayerInputDart(player.getName());
        UserInput userInput = UserInput.prepareUserDartInput(new UserCommunicationService().getUserInput().toString());
        if(!userInput.isValidDart(userInput)) {
            return processDart(player, playerScoreBeforeThrow);
        }
        PossibleDarts parsedInput = PossibleDarts.valueOf(userInput.toString());
        Dart dart = new Dart(parsedInput);
        message.printThrow(player.getName(), dart.getPoints());
        if(playerBusted(player, dart)) {
            message.printPlayerBusted(player.getName(), playerScoreBeforeThrow);
            return DartStatus.BUSTED;
        }
        if(playerCheckedOut(player, dart)) {
            message.printPlayerCheckedOut(player.getName());
            return DartStatus.CHECKOUT;
        }
        this.currentLeg.subtractPlayerScore(player, dart.getPoints());
        message.printRemainingScore(this.currentLeg.getPlayerScore().get(player));
        return DartStatus.NOTHING;
    }

}

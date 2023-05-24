package de.p3lina.application.handle;

import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;

public class HandleThrow implements Handle<Throw, Leg, Object, Object>{

    private Player player;
    private MessagesDuringMatch message;

    public HandleThrow(Player player, Leg currentLeg, MessagesDuringMatch message) {
        this.player = player;
        this.message = message;
    }
    @Override
    public Throw process(Leg currentLeg) {
        Throw dartThrow = new Throw();
        int playerScoreBeforeThrow = currentLeg.getPlayerScore().get(player);
        for(int i = 0; i<3; i++) {
            HandleDart dartHandle = new HandleDart(message);
            Dart dart = dartHandle.process(player);
            dartThrow.addDart(dart);
            //System.out.println(getPlayerAverage(player, dartThrow, currentLeg));
            int currentPlayerScore = currentLeg.getPlayerScore().get(player);
            DartStatus dartStatus = dartHandle.getDartStatus(dart, currentPlayerScore);
            if(dartStatus==DartStatus.BUSTED) {
                message.printPlayerBusted(player.getName(), playerScoreBeforeThrow);
                currentLeg.setPlayerScore(player, playerScoreBeforeThrow);
                dartThrow.setCheckedOut(false);
                break;
            }
            if(dartStatus==DartStatus.CHECKOUT) {
                message.printPlayerCheckedOut(player.getName());
                currentLeg.subtractPlayerScore(player, dart.getPoints());
                dartThrow.setCheckedOut(true);
                break;
            }
            dartThrow.setCheckedOut(false);
            currentLeg.subtractPlayerScore(player, dart.getPoints());
            message.printRemainingScore(currentLeg.getPlayerScore().get(player));
        }
        return dartThrow;
    }

    @Override
    public Object isMatchSetWon(Object something) {
        return null;
    }


    //private double getPlayerAverage(Player player, Throw dartThrow, Leg currentLeg) {

    //}


}

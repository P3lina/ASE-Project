package de.p3lina.application;

import de.p3lina.application.handle.HandleDart;
import de.p3lina.domain.*;
import de.p3lina.domain.messages.MessagesDuringMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandleDartTest {

    String[] userInputLines = {"S21","DDD","1","b","A1","S2","D6","T2","50","SBull"};
    ArrayList dartPointsOutputLines = new ArrayList<>(Arrays.asList(1,2,12,6,50,25));
    Player player;
    MessagesDuringMatch messages;
    HandleDart dartHandle;

    @BeforeEach
    public void setup(){
        this.player = new Player("Player1");
        this.messages = new MessagesDuringMatchMock();
        this.dartHandle = new HandleDart(this.messages);
    }
    @Test
    public void handleDartTest() {
        System.setIn(new ByteArrayInputStream(String.join("\n",userInputLines).getBytes()));
        for(int i=0;i<5;i++) {
            assertEquals(dartPointsOutputLines.remove(0), dartHandle.process(this.player).getPoints());
        }
    }

    @Test
    public void playerDartStatusTest() {
        String[] dartStrings = {"S1", "D2", "T3", "SBull", "DBull"};
        List<Integer> currentPlayerScores = new ArrayList<>(List.of(1,501,3,501,4,9,501,24,501,49,501,50));
        for(String dartString : dartStrings) {
            Dart dart = new Dart(PossibleDarts.valueOf(dartString));
            assertEquals(DartStatus.BUSTED, dartHandle.getDartStatus(dart, currentPlayerScores.remove(0)));
            assertEquals(DartStatus.NOTHING, dartHandle.getDartStatus(dart, currentPlayerScores.remove(0)));
            if(dart.isDoubleNumber()) {
                assertEquals(DartStatus.CHECKOUT, dartHandle.getDartStatus(dart, currentPlayerScores.remove(0)));
            }
        }
    }
}

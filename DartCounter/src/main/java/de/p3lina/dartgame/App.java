package de.p3lina.dartgame;

import de.p3lina.domain.Dart;
import de.p3lina.domain.PossibleDarts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.p3lina.application.HandleMatch;
import de.p3lina.domain.Player;

public class App 
{
    public static void main( String[] args ) throws IOException {
        //Dart dart = new Dart(PossibleDarts.valueOf("Thrown dart, e.g. T20"));
        List<Player> players = new ArrayList<Player>();
        for(int i=0;i<3;i++){
            players.add(new Player("Spieler " + i));
        }
        new HandleMatch().initializeMatch(2,3, players, 301);
    }
}

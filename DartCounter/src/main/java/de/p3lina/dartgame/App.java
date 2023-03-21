package de.p3lina.dartgame;

import de.p3lina.domain.Dart;
import de.p3lina.domain.PossibleDarts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App 
{
    public static void main( String[] args ) throws IOException {
        Dart dart = new Dart(PossibleDarts.valueOf("Thrown dart, e.g. T20"));
    }
}

package de.p3lina.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserCommunicationService {

    public UserInput getUserInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            UserInput userInput = new UserInput(bufferedReader.readLine());
            return userInput;
        }catch(IOException exc) {
        }
        return new UserInput("-1");
    }

}

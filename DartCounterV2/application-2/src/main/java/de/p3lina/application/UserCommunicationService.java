package de.p3lina.application;

import de.p3lina.application.user.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserCommunicationService {

    public UserInput getUserInput() {
        setScannerIfNull();
        Scanner scanner = ScannerStore.getScanner();
        try {
            String s = scanner.nextLine();
            UserInput userInput = new UserInput(s);
            return userInput;
        }catch(Exception exc) {
        }
        return new UserInput("-1");
    }

    private void setScannerIfNull() {
        if(ScannerStore.getScanner()==null) {
            ScannerStore.setScanner(new Scanner(System.in));
        }
    }

}

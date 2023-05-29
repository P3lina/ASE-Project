package de.p3lina.application;

import de.p3lina.application.user.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCommunicationServiceTest {
    private UserCommunicationService userCommunicationService;

    @BeforeEach
    public void setup() {
        userCommunicationService = new UserCommunicationService();
    }

    @Test
    public void getUserInputTest() {
        String testInput = "T20\nS20\nD20\n";
        InputStream in = new ByteArrayInputStream(testInput.getBytes());

        System.setIn(in);

        ScannerStore.setScanner(new Scanner(System.in));


        String[] input = testInput.split("\n");
        for(String inputLine : input) {
            UserInput userInput = userCommunicationService.getUserInput();
            assertEquals(inputLine, userInput.toString());
        }


        System.setIn(System.in);
    }
}
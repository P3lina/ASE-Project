package de.p3lina.application.user;

import de.p3lina.application.user.userInputCases.*;
import de.p3lina.domain.PossibleDarts;

import java.util.Arrays;
import java.util.List;

public class UserInput{
    private String value;


    public UserInput(String value) {
        this.value = value;
    }

    public int toInt() {
        try {
            return Integer.parseInt(value);
        }catch(Exception exc) {

        }
        return -1;
    }

    public String toString() {
        return value;
    }

    public boolean isValidDart(UserInput userInput) {
        try {
            PossibleDarts.valueOf(userInput.toString());
            return true;
        }catch(IllegalArgumentException exc) {
            return false;
        }
    }

    public static UserInput prepareUserDartInput(String userInputString) {
        List<UserInputCase> strategies = Arrays.asList(new UserInputCaseZero(), new UserInputCaseNonNumeric(), new UserInputCaseSBull(), new UserInputCaseDBull(), new UserInputCaseDefaultNumeric());

        for (UserInputCase strategy : strategies) {
            if (strategy.isApplicable(userInputString)) {
                return strategy.userInput(userInputString);
            }
        }

        throw new IllegalArgumentException("Unsupported user input string: " + userInputString);
    }
}

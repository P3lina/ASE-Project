package de.p3lina.application.user.userInputCases;

import de.p3lina.application.user.UserInput;

public class UserInputCaseNonNumeric implements UserInputCase {
    @Override
    public boolean isApplicable(String userInput) {
        return !Character.isDigit(userInput.charAt(0));
    }

    @Override
    public UserInput userInput(String userInput) {
        return new UserInput(userInput.substring(0, 1).toUpperCase() + userInput.substring(1));
    }
}
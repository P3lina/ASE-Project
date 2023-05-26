package de.p3lina.application.user.userInputCases;

import de.p3lina.application.user.UserInput;

public class UserInputCaseDefaultNumeric implements UserInputCase {
    @Override
    public boolean isApplicable(String userInput) {
        return true; // Dies gilt immer dann, wenn keine andere Strategie zutrifft.
    }

    @Override
    public UserInput userInput(String userInput) {
        return new UserInput("S" + userInput);
    }
}
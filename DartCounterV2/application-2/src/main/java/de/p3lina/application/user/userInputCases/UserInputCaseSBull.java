package de.p3lina.application.user.userInputCases;

import de.p3lina.application.user.UserInput;

public class UserInputCaseSBull implements UserInputCase {
    @Override
    public boolean isApplicable(String userInput) {
        return userInput.equals("25");
    }

    @Override
    public UserInput userInput(String userInput) {
        return new UserInput("SBull");
    }
}
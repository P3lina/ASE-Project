package de.p3lina.application.user.userInputCases;

import de.p3lina.application.user.UserInput;

public class UserInputCaseDBull implements UserInputCase {
    @Override
    public boolean isApplicable(String userInput) {
        return userInput.equals("50");
    }

    @Override
    public UserInput userInput(String userInput) {
        return new UserInput("DBull");
    }
}
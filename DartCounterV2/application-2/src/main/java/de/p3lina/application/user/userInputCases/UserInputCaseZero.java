package de.p3lina.application.user.userInputCases;

import de.p3lina.application.user.UserInput;

public class UserInputCaseZero implements UserInputCase {
    @Override
    public boolean isApplicable(String userInputString) {
        return Character.isDigit(userInputString.charAt(0)) && userInputString.length()==1 && Integer.parseInt(userInputString)==0;
    }

    @Override
    public UserInput userInput(String userInputString) {
        return new UserInput("Zero");
    }
}

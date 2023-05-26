package de.p3lina.application.user.userInputCases;

import de.p3lina.application.user.UserInput;

public interface UserInputCase {
    boolean isApplicable(String userInputString);
    UserInput userInput(String userInputString);
}
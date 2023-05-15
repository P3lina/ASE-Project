package de.p3lina.application;

import de.p3lina.domain.PossibleDarts;

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
        if(userInputString.length()==0) {
            return new UserInput(userInputString);
        }
        if(Character.isDigit(userInputString.charAt(0)) && userInputString.length()==1 && Integer.parseInt(userInputString)==0) {
            return new UserInput("Zero");
        }
        if(!Character.isDigit(userInputString.charAt(0))) {
            return new UserInput(userInputString.substring(0, 1).toUpperCase() + userInputString.substring(1));
        }
        if(userInputString.equals("25")) {
            return new UserInput("SBull");
        }
        if(userInputString.equals("50")) {
            return new UserInput("DBull");
        }
        StringBuilder sb = new StringBuilder("S");
        sb.append(userInputString);
        return new UserInput(sb.toString());
    }
}

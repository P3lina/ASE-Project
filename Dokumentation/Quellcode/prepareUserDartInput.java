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
        return new UserInput("S" + userInputString);
}
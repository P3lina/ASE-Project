package de.p3lina.application;

import java.text.ParseException;

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
}

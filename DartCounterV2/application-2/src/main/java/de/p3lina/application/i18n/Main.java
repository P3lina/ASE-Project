package de.p3lina.application.i18n;

import de.p3lina.domain.i18n.Messages;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        I18n greeter = new I18n();
        String greeting = greeter.getMessage(Messages.START_SCORE, Locale.ENGLISH);
        System.out.println(greeting);
    }
}

package de.p3lina.application.i18n;

import de.p3lina.domain.i18n.Messages;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

    private static final String BUNDLE_NAME = "de.p3lina.messages.resource_bundle";

    public static String getMessage(Messages message, Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return rb.getString(message.toString());
    }

    public static String getMessage(Messages message) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
        return rb.getString(message.toString());
    }
}

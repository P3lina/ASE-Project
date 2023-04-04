package de.p3lina.adapters.i18n;

import de.p3lina.domain.i18n.Messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

    private static final String BUNDLE_NAME = "de.p3lina.messages.resource_bundle";

    public static String getMessage(Messages messageCode, Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return rb.getString(messageCode.toString());
    }

    public static String getMessage(Messages messageCode) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
        return rb.getString(messageCode.toString());
    }

    public static <T> String getMessage(Messages messageCode, T ...parameters) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
        String messageWithPlaceholders = rb.getString(messageCode.toString());
        String message = MessageFormat.format(messageWithPlaceholders, parameters);
        return message;
    }
}

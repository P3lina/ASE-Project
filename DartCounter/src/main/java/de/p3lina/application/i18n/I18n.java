package de.p3lina.application.i18n;

import de.p3lina.domain.i18n.Messages;

import java.util.ResourceBundle;

public class I18n {

    public static String getMessage(Messages message) {
        ResourceBundle rb = ResourceBundle.getBundle("messages.resource_bundle");
        return rb.getString(message.toString());
    }
}

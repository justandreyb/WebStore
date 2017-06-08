package com.training.web_store.command.impl.interaction;

import com.training.web_store.command.impl.InteractionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleCommand extends InteractionCommand {
    private static final String TARGET_LOCALE = "targetLocale";
    private static final String LOCALE = "locale";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String targetLocale = request.getParameter(TARGET_LOCALE);

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(targetLocale);
        session.setAttribute(LOCALE, locale);

    }
}

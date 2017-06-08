package com.training.web_store.util;

import com.training.web_store.bean.account.User;

import java.util.Date;

public class ArgumentParserUtil {

    public static boolean isValidArgument(String argument) {
        boolean result = true;
        if (argument == null) {
            result = false;
        } else if (argument.isEmpty()) {
            result = false;
        }
        return result;
    }

    public static boolean isValidArgument(Date date) {
        return date != null && date.getTime() != 0;
    }

    public static boolean isValidArgument(int number) {
        return number > 0;
    }

    public static boolean isValidArgument(double number) {
        return number > 0;
    }

    public static boolean areValidArguments(String ... arguments) {
        boolean validArguments = true;
        for (String argument : arguments) {
            validArguments = isValidArgument(argument);
        }

        return validArguments;
    }

    public static boolean areValidArguments(int ... numbers) {
        boolean validArguments = true;
        for (int argument : numbers) {
            validArguments = isValidArgument(argument);
        }

        return validArguments;
    }

    public static boolean areValidArguments(Date ... dates) {
        boolean validArguments = true;
        for (Date argument : dates) {
            validArguments = isValidArgument(argument);
        }

        return validArguments;
    }

    public static boolean isValidPassword(String password) {
        boolean result = true;
        if (password.length() < 6) {
            result = false;
        }
        return result;
    }

    public static boolean isValidLogin(String email) {
        boolean valid = true;

        if (!email.contains("@")) {
            valid = false;
        }

        return valid;
    }

    public static boolean isValidUser(User user) {
        boolean result = true;
        if (!isValidLogin(user.getEmail())) {
            result = false;
        }
        if (!isValidPassword(user.getPassword())) {
            result = false;
        }
        if (!areValidArguments(user.getFirstName(), user.getLastName(), user.getLocale())) {
            result = false;
        }
        return result;
    }
}

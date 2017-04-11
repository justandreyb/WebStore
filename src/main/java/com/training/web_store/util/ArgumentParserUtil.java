package com.training.web_store.util;

import com.training.web_store.bean.account.User;

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

    public static boolean isValidArguments(String ... arguments) {
        boolean validArguments = true;
        for (String argument : arguments) {
            validArguments = isValidArgument(argument);
        }

        return validArguments;
    }

    public static boolean isValidPassword(String password) {
        boolean result = true;

        return result;
    }

    public static boolean isValidLogin(String email) {
        boolean valid = true;

        if (!valid) {
            return false;
        }

        if (!email.contains("@")) {
            valid = false;
        }

        return valid;
    }

    public static boolean isValidUser(User user) {
        return isValidArguments(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getLocale());
    }
}

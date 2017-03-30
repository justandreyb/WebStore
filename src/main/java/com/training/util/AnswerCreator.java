package com.training.util;

import org.json.simple.JSONObject;

public class AnswerCreator {

    private static final String DATA = "data";
    private static final String ERROR = "error";

    public static String create(String data) {
        return createJSON(DATA, data);
    }

    public static String createError(String errorMessage) {
        return createJSON(ERROR, errorMessage);
    }

    private static String createJSON(String key, String value) {
        JSONObject answerJSON = new JSONObject();
        answerJSON.put(key, value);
        return answerJSON.toString();
    }

}

package com.training.web_store.util;

import java.sql.Date;

public class ArgumentExchanger {

    public static Date exchangeToSQLDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date exchangeFromSQLDate(Date date) {
        return new java.util.Date(date.getTime());
    }
}

package com.training.web_store.util.database;

import java.util.ResourceBundle;

public class DatabaseResourcesMapper {
    private static final String BUNDLE_PATH = "com.training.web_store.dao.db";

    private static final DatabaseResourcesMapper instance = new DatabaseResourcesMapper();
    private ResourceBundle properties = ResourceBundle.getBundle(BUNDLE_PATH);

    public static DatabaseResourcesMapper getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return properties.getString(key);
    }
}

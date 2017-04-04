package com.training.pages_creator.bean;

import java.util.HashMap;
import java.util.Map;

public class CollectedData {
    private Map<String, String> parameters;

    public CollectedData() {
        this.parameters = new HashMap<String, String>();
    }

    public void addObject(String name, String value) {
        this.parameters.put(name, value);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}

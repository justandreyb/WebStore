package com.training.pages_creator.bean;

public class ParsedRequest {
    private String entity;
    private String formType;
    private String command;

    public ParsedRequest(String entity, String formType, String command) {
        this.entity = entity;
        this.formType = formType;
        this.command = command;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
}

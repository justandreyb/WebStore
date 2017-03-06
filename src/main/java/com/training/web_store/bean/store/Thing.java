package com.training.web_store.bean.store;

import java.util.Date;

public class Thing {
    private String name;
    private Category category;
    private Brand brand;
    private String description;
    private Date creationDate;
    private String review;

    public Thing(String name, String description, Date creationDate) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
    }
}

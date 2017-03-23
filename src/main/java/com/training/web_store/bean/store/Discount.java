package com.training.web_store.bean.store;

import java.util.Date;

public class Discount {
    private int id;
    private byte value;
    private Date startDate;
    private Date finishDate;

    public Discount() {
    }

    public Discount(byte value, Date startDate, Date finishDate) {
        this.value = value;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}

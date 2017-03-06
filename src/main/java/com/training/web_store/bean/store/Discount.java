package com.training.web_store.bean.store;

import java.util.Date;

public class Discount {
    private byte value;
    private Date startDate;
    private Date finishDate;

    public Discount(byte value, Date startDate, Date finishDate) {
        this.value = value;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}

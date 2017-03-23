package com.training.web_store.bean.store;

import java.util.Map;

public class Product {
    private int id;
    private String name;
    private double price;
    private Discount discount;
    private Map<Byte, Thing> things;

    public Product() {
    }

    public Product(String name, int categoryId, int discountId) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Map<Byte, Thing> getThings() {
        return things;
    }

    public void setThings(Map<Byte, Thing> things) {
        this.things = things;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

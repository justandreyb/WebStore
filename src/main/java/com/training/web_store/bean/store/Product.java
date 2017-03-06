package com.training.web_store.bean.store;

import java.util.List;

public class Product {
    private String name;
    private double price;
    private Discount discount;
    private List<Photo> photos;

    public Product(String name, int categoryId, int discountId) {
    }
}

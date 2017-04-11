package com.training.util;

import com.training.web_store.bean.store.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

@SuppressWarnings("Duplicates")

//TODO: Write !
public class AnswerCreator {

    private static final String DATA = "data";
    private static final String ERROR = "error";

    private static final String ID = "id";

    private static final String BRAND = "brand";
    private static final String BRANDS = "brands";
    private static final String BRAND_NAME = "name";
    private static final String BRAND_DESCRIPTION = "description";

    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_DESCRIPTION = "description";


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

    public static String createJSONFromBrand(Brand brand) {
        JSONObject brandJSON = new JSONObject();
        brandJSON.put(ID, brand.getId());
        brandJSON.put(BRAND_NAME, brand.getName());
        brandJSON.put(BRAND_DESCRIPTION, brand.getDescription());

        return brandJSON.toString();
    }

    public static String createJSONFromBrands(List<Brand> brands) {
        JSONObject brandsJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Brand brand : brands) {
            objects.add(createJSONFromBrand(brand));
        }
        brandsJSON.put(BRANDS, objects);

        return brandsJSON.toString();
    }

    public static String createJSONFromCategory(Category category) {
        JSONObject categoryJSON = new JSONObject();
        categoryJSON.put(ID, category.getId());
        categoryJSON.put(CATEGORY_NAME, category.getName());
        categoryJSON.put(CATEGORY_DESCRIPTION, category.getDescription());

        return categoryJSON.toString();
    }

    public static String createJSONFromCategories(List<Category> categories) {
        JSONObject categoryJSON = new JSONObject();
        categoryJSON.put(ID, category.getId());
        categoryJSON.put(CATEGORY_NAME, category.getName());
        categoryJSON.put(CATEGORY_DESCRIPTION, category.getDescription());

        return categoryJSON.toString();
    }

    public static String createJSONFromProduct(Product product) {
        return null;
    }

    public static String createJSONFromProducts(List<Product> products) {
        return null;
    }

    public static String createJSONFromPhotos(List<Photo> photos) {
        return null;
    }

    public static String createJSONFromDiscount(Discount discount) {
        return null;
    }

    public static String createJSONFromDiscounts(List<Discount> discounts) {
        return null;
    }

    public static String createJSONFromReview(Review review) {
        return null;
    }

    public static String createJSONFromReviews(List<Review> reviews) {
        return null;
    }

    public static String createJSONFromThing(Thing thing) {
        return null;
    }

    public static String createJSONFromThings(List<Thing> things) {
        return null;
    }
}

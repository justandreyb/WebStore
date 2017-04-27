package com.training.util;

import com.training.web_store.bean.account.Role;
import com.training.web_store.bean.account.User;
import com.training.web_store.bean.store.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

@SuppressWarnings("Duplicates")
public class AnswerCreator {

    private static final String DATA = "data";
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

    private static final String ID = "id";

    private static final String BRANDS = "brands";
    private static final String BRAND_NAME = "name";
    private static final String BRAND_DESCRIPTION = "description";

    private static final String CATEGORIES = "categories";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_DESCRIPTION = "description";

    private static final String PRODUCTS = "products";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_CATEGORY = "category";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_DISCOUNT = "discount";

    private static final String PHOTOS = "photos";
    private static final String PHOTO_HREF = "href";
    private static final String PHOTO_REAL_NAME = "realName";

    private static final String DISCOUNTS = "discounts";
    private static final String DISCOUNT_VALUE = "value";
    private static final String DISCOUNT_START_DATE = "startDate";
    private static final String DISCOUNT_FINISH_DATE = "finishDate";

    private static final String THINGS = "things";
    private static final String THING_NAME = "name";
    private static final String THING_CATEGORY = "category";
    private static final String THING_BRAND = "brand";
    private static final String THING_DESCRIPTION = "description";
    private static final String THING_CREATION_DATE = "creationDate";

    private static final String ACCOUNTS = "accounts";
    private static final String ACCOUNT_EMAIL = "email";
    private static final String ACCOUNT_FIRST_NAME = "firstName";

    private static final Object ROLES = "roles";
    private static final Object ROLE_VALUE = "value";

    public static String create(String data) {
        return createJSON(DATA, data);
    }

    public static String createError(String errorMessage) {
        return createJSON(ERROR, errorMessage);
    }

    private static String createJSON(String key, String value) {
        JSONObject answerJSON = new JSONObject();
        answerJSON.put(key, value);
        return answerJSON.toJSONString();
    }

    public static String createSuccess(String message) {
        return createJSON(SUCCESS, message);
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONBrand(Brand brand) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, brand.getId());
        entityJSON.put(BRAND_NAME, brand.getName());
        entityJSON.put(BRAND_DESCRIPTION, brand.getDescription());
        return entityJSON;
    }

    public static String createJSONFromBrand(Brand brand) {
        return createJSONBrand(brand).toJSONString();
    }

    public static String createJSONFromBrands(List<Brand> brands) {
        JSONObject brandsJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Brand brand : brands) {
            objects.add(createJSONBrand(brand));
        }
        brandsJSON.put(BRANDS, objects);

        return brandsJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONCategory(Category category) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, category.getId());
        entityJSON.put(CATEGORY_NAME, category.getName());
        entityJSON.put(CATEGORY_DESCRIPTION, category.getDescription());
        return entityJSON;
    }

    public static String createJSONFromCategory(Category category) {
        return createJSONCategory(category).toJSONString();
    }

    public static String createJSONFromCategories(List<Category> categories) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Category category : categories) {
            objects.add(createJSONCategory(category));
        }
        entitiesJSON.put(CATEGORIES, objects);

        return entitiesJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONProduct(Product product) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, product.getId());
        entityJSON.put(PRODUCT_NAME, product.getName());
        entityJSON.put(PRODUCT_PRICE, product.getPrice());
        entityJSON.put(PRODUCT_CATEGORY, product.getCategory());
        entityJSON.put(PRODUCT_DISCOUNT, product.getDiscount());
        List<Thing> thingList = product.getThings();
        if (thingList != null && thingList.size() != 0) {
            JSONArray things = new JSONArray();
            for (Thing thing : product.getThings()) {
                things.add(createJSONThing(thing));
            }
            entityJSON.put(THINGS, things);
        }
        List<Photo> photoList = product.getPhotos();
        if (photoList != null && photoList.size() != 0) {
            JSONArray photos = new JSONArray();
            for (Photo photo : product.getPhotos()) {
                photos.add(createJSONPhoto(photo));
            }
            entityJSON.put(PHOTOS, photos);
        }

        return entityJSON;
    }

    public static String createJSONFromProduct(Product product) {
        return createJSONProduct(product).toJSONString();
    }

    public static String createJSONFromProducts(List<Product> products) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Product product : products) {
            objects.add(createJSONProduct(product));
        }
        entitiesJSON.put(PRODUCTS, objects);

        return entitiesJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONPhoto(Photo photo) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, photo.getId());
        entityJSON.put(PHOTO_HREF, photo.getHref());
        entityJSON.put(PHOTO_REAL_NAME, photo.getRealName());
        return entityJSON;
    }

    public static String createJSONFromPhotos(List<Photo> photos) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Photo photo : photos) {
            objects.add(createJSONPhoto(photo));
        }
        entitiesJSON.put(PHOTOS, objects);

        return entitiesJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONDiscount(Discount discount) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, discount.getId());
        entityJSON.put(DISCOUNT_VALUE, discount.getValue());
        entityJSON.put(DISCOUNT_START_DATE, discount.getStartDate().toString());
        entityJSON.put(DISCOUNT_FINISH_DATE, discount.getFinishDate().toString());
        return entityJSON;
    }

    public static String createJSONFromDiscount(Discount discount) {
        return createJSONDiscount(discount).toJSONString();
    }

    public static String createJSONFromDiscounts(List<Discount> discounts) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Discount discount : discounts) {
            objects.add(createJSONDiscount(discount));
        }
        entitiesJSON.put(DISCOUNTS, objects);

        return entitiesJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONThing(Thing thing) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, thing.getId());
        entityJSON.put(THING_NAME, thing.getName());
        entityJSON.put(THING_CATEGORY, thing.getCategory());
        entityJSON.put(THING_BRAND, thing.getBrand());
        entityJSON.put(THING_DESCRIPTION, thing.getDescription());
        entityJSON.put(THING_CREATION_DATE, thing.getCreationDate().toString());
        JSONArray photos = new JSONArray();
        List<Photo> photosList = thing.getPhotos();
        if (photosList != null) {
            for (Photo photo : thing.getPhotos()) {
                photos.add(createJSONPhoto(photo));
            }
            entityJSON.put(PHOTOS, photos);
        }
        return entityJSON;
    }

    public static String createJSONFromThing(Thing thing) {
        return createJSONThing(thing).toJSONString();
    }

    public static String createJSONFromThings(List<Thing> things) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Thing thing : things) {
            objects.add(createJSONThing(thing));
        }
        entitiesJSON.put(THINGS, objects);

        return entitiesJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    public static String createJSONFromReview(String review) {
        JSONObject reviewJSON = new JSONObject();

        reviewJSON.put("review", review);

        return reviewJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    public static String createJSONFromOrder(Order order) {
        return "";
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONAccount(User user) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, user.getId());
        entityJSON.put(ACCOUNT_EMAIL, user.getEmail());
        entityJSON.put(ACCOUNT_FIRST_NAME, user.getFirstName());

        return entityJSON;
    }

    public static String createJSONFromAccounts(List<User> users) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (User user : users) {
            objects.add(createJSONAccount(user));
        }
        entitiesJSON.put(ACCOUNTS, objects);

        return entitiesJSON.toJSONString();
    }

    /*-------------------------------------------------------------------*/

    private static JSONObject createJSONRole(Role role) {
        JSONObject entityJSON = new JSONObject();
        entityJSON.put(ID, role.getId());
        entityJSON.put(ROLE_VALUE, role.getValue());

        return entityJSON;
    }

    public static String createJSONFromRoles(List<Role> roles) {
        JSONObject entitiesJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        for (Role role : roles) {
            objects.add(createJSONRole(role));
        }
        entitiesJSON.put(ROLES, objects);

        return entitiesJSON.toJSONString();
    }
}

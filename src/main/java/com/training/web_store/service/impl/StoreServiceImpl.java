package com.training.web_store.service.impl;

import com.training.web_store.bean.account.Role;
import com.training.web_store.bean.store.*;
import com.training.web_store.dao.*;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.ArgumentExchanger;
import com.training.web_store.util.ArgumentParserUtil;
import com.training.web_store.util.exception.StorageException;

import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")
public class StoreServiceImpl implements StoreService {
    private final DAOFactory factory = DAOFactory.getInstance();
    private final BrandDAO brandDAO = factory.getBrandDAO();
    private final CategoryDAO categoryDAO = factory.getCategoryDAO();
    private final DiscountDAO discountDAO = factory.getDiscountDAO();
    private final PhotoDAO photoDAO = factory.getPhotoDAO();
    private final ProductDAO productDAO = factory.getProductDAO();
    private final ThingDAO thingDAO = factory.getThingDAO();
    private final RoleDAO roleDAO = factory.getRoleDAO();

    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Override
    public void addBrand(String name) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            brandDAO.addBrand(name);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addBrand(String name, String description) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(name, description)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            brandDAO.addBrand(name, description);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Brand getBrand(int brandId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(brandId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            return brandDAO.getBrand(brandId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Brand> getBrands() throws ServiceException {
        try {
            return brandDAO.getBrands();
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBrand(int brandId, String name, String description) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(brandId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.areValidArguments(name, description)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            brandDAO.updateBrand(brandId, name, description);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBrand(int brandId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(brandId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            brandDAO.setBrandAvailable(brandId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

/* --------------------------------------------------------------------------- */

    @Override
    public void addCategory(String name) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            categoryDAO.addCategory(name);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCategory(String name, String description) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            categoryDAO.addCategory(name, description);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Category getCategory(int categoryId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            return categoryDAO.getCategory(categoryId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Category> getCategories() throws ServiceException {
        try {
            return categoryDAO.getCategories();
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCategory(int categoryId, String name, String description) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.areValidArguments(name, description)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            categoryDAO.updateCategory(categoryId, name, description);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCategory(int categoryId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            categoryDAO.setAvailable(categoryId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

/* ---------------------------------------------------------------------------- */

    @Override
    public void addDiscount(byte value, Date startDate, Date finishDate) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(value)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.areValidArguments(startDate, finishDate)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        java.sql.Date startDateSQL = ArgumentExchanger.exchangeToSQLDate(startDate);
        java.sql.Date finishDateSQL = ArgumentExchanger.exchangeToSQLDate(finishDate);
        Discount discount = new Discount(value, startDateSQL, finishDateSQL);
        try {
            discountDAO.addDiscount(discount);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateDiscount(int id, byte value, Date startDate, Date finishDate) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(id, value)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.areValidArguments(startDate, finishDate)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        java.sql.Date startDateSQL = ArgumentExchanger.exchangeToSQLDate(startDate);
        java.sql.Date finishDateSQL = ArgumentExchanger.exchangeToSQLDate(finishDate);
        Discount discount = new Discount(value, startDateSQL, finishDateSQL);
        try {
            discountDAO.updateDiscount(id, discount);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Discount getDiscount(int discountId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(discountId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            Discount discount = discountDAO.getDiscount(discountId);

            Date startDate = ArgumentExchanger.exchangeFromSQLDate((java.sql.Date) discount.getStartDate());
            Date finishDate = ArgumentExchanger.exchangeFromSQLDate((java.sql.Date) discount.getFinishDate());
            discount.setStartDate(startDate);
            discount.setFinishDate(finishDate);

            return discount;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Discount> getDiscounts() throws ServiceException {
        try {
            List<Discount> discounts = discountDAO.getDiscounts();
            for (Discount discount : discounts) {
                Date startDate = ArgumentExchanger.exchangeFromSQLDate((java.sql.Date) discount.getStartDate());
                Date finishDate = ArgumentExchanger.exchangeFromSQLDate((java.sql.Date) discount.getFinishDate());
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);
            }
            return discounts;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Discount> getDiscounts(Date date) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(date)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            java.sql.Date requestedDate = ArgumentExchanger.exchangeToSQLDate(date);
            List<Discount> discounts = discountDAO.getDiscountForDate(requestedDate);
            for (Discount discount : discounts) {
                Date startDate = ArgumentExchanger.exchangeFromSQLDate((java.sql.Date) discount.getStartDate());
                Date finishDate = ArgumentExchanger.exchangeFromSQLDate((java.sql.Date) discount.getFinishDate());
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);
            }
            return discounts;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteDiscount(int discountId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(discountId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            discountDAO.setDiscountAvailable(discountId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

/* ----------------------------------------------------------------------- */

    @Override
    public void addPhotoForThing(int thingId, String name) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        //TODO: create storagePath here !
        try {
            photoDAO.addThingPhoto(thingId, name, name);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addPhotoForProduct(int productId, String name) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(productId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        //TODO: create storagePath here !
        try {
            photoDAO.addProductPhoto(productId, name, name);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Photo> getPhotos(int thingId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            return photoDAO.getPhotosForThing(thingId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePhoto(int photoId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(photoId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            photoDAO.setAvailable(photoId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

/* ----------------------------------------------------------------- */

    @Override
    public void addProduct(String name, double price, int categoryId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(price)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.addProduct(name, price, categoryId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addProduct(String name, double price, int categoryId, int discountId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(price)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.addProduct(name, price, categoryId, discountId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addThingToProduct(int productId, int thingId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(productId, thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.addThing(productId, thingId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteThingFromProduct(int productId, int thingId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(productId, thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.removeThing(productId, thingId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product getProduct(int productId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(productId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            Product product = productDAO.getProduct(productId);
            List<Photo> photosForProduct = photoDAO.getPhotosForProduct(productId);
            product.setPhotos(photosForProduct);
            List<Thing> things = thingDAO.getThingsForProduct(productId);
            for (Thing thing : things) {
                List<Photo> photosForThing = photoDAO.getPhotosForThing(thing.getId());
                thing.setPhotos(photosForThing);
            }
            product.setThings(things);
            return product;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    //TODO: Who will use this method?
    @Override
    public Product getProduct(String name, int categoryId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            Product product = productDAO.getProduct(name, categoryId);
            List<Photo> photosForProduct = photoDAO.getPhotosForProduct(product.getId());
            product.setPhotos(photosForProduct);
            List<Thing> things = thingDAO.getThingsForProduct(product.getId());
            for (Thing thing : things) {
                List<Photo> photosForThing = photoDAO.getPhotosForThing(thing.getId());
                thing.setPhotos(photosForThing);
            }
            product.setThings(things);
            return product;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProducts() throws ServiceException {
        try {
            List<Product> products = productDAO.getProducts();
            for (Product product : products) {
                List<Photo> photos = photoDAO.getPhotosForProduct(product.getId());
                product.setPhotos(photos);
                List<Thing> things = thingDAO.getThingsForProduct(product.getId());
                product.setThings(things);
            }
            return products;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> searchProduct(String requestedProduct) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(requestedProduct)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            List<Product> products = productDAO.searchProduct(requestedProduct);
            for (Product product : products) {
                List<Photo> photos = photoDAO.getPhotosForProduct(product.getId());
                product.setPhotos(photos);
                List<Thing> things = thingDAO.getThingsForProduct(product.getId());
                product.setThings(things);
            }
            return products;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsForCategory(int categoryId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            List<Product> products = productDAO.getProductsForCategory(categoryId);
            for (Product product : products) {
                List<Photo> photos = photoDAO.getPhotosForProduct(product.getId());
                product.setPhotos(photos);
                List<Thing> things = thingDAO.getThingsForProduct(product.getId());
                product.setThings(things);
            }
            return products;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsForBrand(int brandId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(brandId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            List<Product> products = productDAO.getProductsForBrand(brandId);
            for (Product product : products) {
                List<Photo> photos = photoDAO.getPhotosForProduct(product.getId());
                product.setPhotos(photos);
                List<Thing> things = thingDAO.getThingsForProduct(product.getId());
                product.setThings(things);
            }
            return products;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateProduct(int productId, String name, double price, int categoryId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(productId, categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(price)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.updateProduct(productId, name, price, categoryId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateProduct(int productId, String name, double price, int categoryId, int discountId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(productId, categoryId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(price)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(name)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.updateProduct(productId, name, price, categoryId, discountId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(productId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            productDAO.setProductAvailable(productId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    /* --------------------------------------------------------------------- */

    @Override
    public void addThing(String name, String description, Date creationDate, int categoryId, int brandId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(name, description)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.areValidArguments(categoryId, brandId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(creationDate)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        java.sql.Date creationDateSQL = ArgumentExchanger.exchangeToSQLDate(creationDate);
        Thing thing = new Thing(name, description, creationDateSQL);
        try {
            thingDAO.addThing(thing, categoryId, brandId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Thing getThing(int thingId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            Thing thing = thingDAO.getThing(thingId);
            List<Photo> photos = photoDAO.getPhotosForThing(thingId);
            thing.setPhotos(photos);
            return thing;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Thing> getThings() throws ServiceException {
        try {
            List<Thing> things = thingDAO.getThings();
            for (Thing thing : things) {
                byte rating = thingDAO.getRating(thing.getId());
                if (rating != 0) {
                    thing.setRating(rating);
                }
            }
            return things;
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateThing(int thingId, String name, String description, Date creationDate, int categoryId, int brandId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(thingId, categoryId, brandId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.areValidArguments(name, description)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(creationDate)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            java.sql.Date creationDateSQL = new java.sql.Date(creationDate.getTime());
            thingDAO.updateThing(thingId, name, description, creationDateSQL, categoryId, brandId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteThing(int thingId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            thingDAO.setThingAvailable(thingId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    /* --------------------------------------------------------------------- */

    @Override
    public void addRating(int userId, int thingId, byte value) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(thingId, thingId, value)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            thingDAO.addRating(userId, thingId, value);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public byte getRating(int thingId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            return thingDAO.getRating(thingId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteRating(int thingId, int userId) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(thingId, userId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            thingDAO.deleteRating(thingId, userId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    /* --------------------------------------------------------------------- */

    @Override
    public void addReview(int thingId, String review) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidArgument(review)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            thingDAO.addThingReview(thingId, review);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getReview(int thingId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            return thingDAO.getThingReview(thingId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteReview(int thingId) throws ServiceException {
        if (!ArgumentParserUtil.isValidArgument(thingId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            thingDAO.deleteThingReview(thingId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Role> getRoles() throws ServiceException {
        try {
            return roleDAO.getRoles();
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }
}

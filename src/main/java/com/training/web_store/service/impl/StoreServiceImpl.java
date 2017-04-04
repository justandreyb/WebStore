package com.training.web_store.service.impl;

import com.training.web_store.bean.store.*;
import com.training.web_store.dao.*;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public class StoreServiceImpl implements StoreService {
    private final DAOFactory factory = DAOFactory.getInstance();
    private final BrandDAO brandDAO = factory.getBrandDAO();
    private final CategoryDAO categoryDAO = factory.getCategoryDAO();
    private final DiscountDAO discountDAO = factory.getDiscountDAO();
    private final PhotoDAO photoDAO = factory.getPhotoDAO();
    private final ProductDAO productDAO = factory.getProductDAO();
    private final ThingDAO thingDAO = factory.getThingDAO();

    @Override
    public void addBrand(String name) throws ServiceException {
        try {
            brandDAO.addBrand(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addBrand(String name, String description) throws ServiceException {
        try {
            brandDAO.addBrand(name, description);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Brand getBrand(int brandId) throws ServiceException {
        try {
            return brandDAO.getBrand(brandId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Brand> getBrands() throws ServiceException {
        try {
            return brandDAO.getBrands();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBrand(int brandId, String name, String description) throws ServiceException {
        try {
            brandDAO.updateBrand(brandId, name, description);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBrand(int brandId) throws ServiceException {
        try {
            brandDAO.setBrandAvailable(brandId, false);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

/* --------------------------------------------------------------------------- */

    @Override
    public void addCategory(String name) throws ServiceException {
        try {
            categoryDAO.addCategory(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCategory(String name, String description) throws ServiceException {
        try {
            categoryDAO.addCategory(name, description);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Category getCategory(int categoryId) throws ServiceException {
        try {
            return categoryDAO.getCategory(categoryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Category> getCategories() throws ServiceException {
        try {
            return categoryDAO.getCategories();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCategory(int categoryId, String name, String description) throws ServiceException {
        try {
            categoryDAO.updateCategory(categoryId, name, description);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCategory(int categoryId) throws ServiceException {
        try {
            categoryDAO.setAvailable(categoryId, false);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

/* ---------------------------------------------------------------------------- */

    @Override
    public void addDiscount(byte value, Date startDate, Date finishDate) throws ServiceException {
        Discount discount = new Discount(value, startDate, finishDate);
        try {
            discountDAO.addDiscount(discount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Discount getDiscount(int discountId) throws ServiceException {
        try {
            return discountDAO.getDiscount(discountId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Discount> getDiscounts() throws ServiceException {
        try {
            //TODO: exchange util.date to sql.date
            return discountDAO.getDiscounts();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Discount> getDiscounts(Date date) throws ServiceException {
        try {
            //exchange util.date to sql.date
            return discountDAO.getDiscountForDate(null);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteDiscount(int discountId) throws ServiceException {
        try {
            discountDAO.setDiscountAvailable(discountId, false);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

/* ----------------------------------------------------------------------- */

    @Override
    public void addPhoto(int thingId, String name) throws ServiceException {
        //TODO: create storagePath here !
        try {
            photoDAO.addThingPhoto(thingId, name, name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Photo> getPhotos(int thingId) throws ServiceException {
        try {
            return photoDAO.getPhotos(thingId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePhoto(int photoId) throws ServiceException {
        try {
            photoDAO.setAvailable(photoId, false);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

/* ----------------------------------------------------------------- */

    @Override
    public void addProduct(String name, double price, int categoryId) throws ServiceException {
        try {
            productDAO.addProduct(name, price, categoryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addProduct(String name, double price, int categoryId, int discountId) throws ServiceException {
        try {
            productDAO.addProduct(name, price, categoryId, discountId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addThing(int productId, int thingId) throws ServiceException {

    }

    @Override
    public void removeThing(int productId, int thingId) throws ServiceException {

    }

    @Override
    public Product getProduct(String name, int categoryId) throws ServiceException {
        try {
            return productDAO.getProduct(name, categoryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProducts(String requestedProduct) throws ServiceException {
        try {
            return productDAO.getProducts(requestedProduct);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsForCategory(int categoryId) throws ServiceException {
        try {
            return productDAO.getProducts(categoryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductsForBrand(int brandId) throws ServiceException {
        return null;
    }

    @Override
    public void updateProduct(int productId, String name, double price, int categoryId, int discountId) throws ServiceException {
        try {
            productDAO.updateProduct(productId, name, price, categoryId, discountId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws ServiceException {
        try {
            productDAO.setProductAvailable(productId, false);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

/* --------------------------------------------------------------------- */

    @Override
    public void addThing(String name, String description, Date creationDate) throws ServiceException {
        Thing thing = new Thing(name, description, creationDate);
        try {
            thingDAO.addThing(thing);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addReview(int thingId, String review) throws ServiceException {
        try {
            thingDAO.addThingReview(thingId, review);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addRating(int userId, int thingId, byte value) throws ServiceException {
        try {
            thingDAO.addRating(userId, thingId, value);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Thing getThing(int thingId) throws ServiceException {
        try {
            return thingDAO.getThing(thingId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public byte getRating(int thingId) throws ServiceException {
        try {
            return thingDAO.getRating(thingId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateThing(int thingId, String name, String description, Date creationDate) throws ServiceException {
        try {
            //TODO: Exchange util.date to sql.date
            thingDAO.updateThing(thingId, name, description, null);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteThing(int thingId) throws ServiceException {
        try {
            thingDAO.setThingAvailable(thingId, false);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

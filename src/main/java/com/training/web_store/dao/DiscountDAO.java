package com.training.web_store.dao;

import com.training.web_store.bean.store.Discount;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.sql.Date;
import java.util.List;

public interface DiscountDAO {
    void addDiscount(Discount discount) throws DAOException, StorageException;

    void updateDiscount(int discountId, Discount discount) throws DAOException, StorageException;

    Discount getDiscount(int discountId) throws DAOException, StorageException;
    List<Discount> getDiscounts() throws DAOException, StorageException;
    List<Discount> getDiscountForDate(Date date) throws DAOException, StorageException;

    void setDiscountAvailable(int discountId, boolean available) throws DAOException, StorageException;
}

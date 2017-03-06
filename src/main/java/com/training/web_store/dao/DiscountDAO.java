package com.training.web_store.dao;

import com.training.web_store.bean.store.Discount;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface DiscountDAO {
    void addDiscount(Discount discount) throws DAOException;

    void updateDiscount(int discountId, Discount discount) throws DAOException;

    Discount getDiscount(int discountId) throws DAOException;
    List<Discount> getDiscountForDate(Date date) throws DAOException;

    void setDiscountAvailable(int discountId, boolean available) throws DAOException;
}

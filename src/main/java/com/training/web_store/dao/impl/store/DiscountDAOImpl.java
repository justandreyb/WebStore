package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Discount;
import com.training.web_store.dao.DiscountDAO;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {
    //TODO: Write
    @Override
    public void addDiscount(Discount discount) throws DAOException {

    }

    @Override
    public void updateDiscount(int discountId, Discount discount) throws DAOException {

    }

    @Override
    public Discount getDiscount(int discountId) throws DAOException {
        return null;
    }

    @Override
    public List<Discount> getDiscountForDate(Date date) throws DAOException {
        return null;
    }

    @Override
    public void setDiscountAvailable(int discountId, boolean available) throws DAOException {

    }
}

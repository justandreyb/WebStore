package com.training.web_store.dao.impl.store;

import com.training.web_store.dao.DiscountDAO;
import com.training.web_store.bean.store.Discount;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.ArgumentExchanger;
import com.training.web_store.dao.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class DiscountDAOImpl implements DiscountDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String DISCOUNT_TABLE = "discount";
    private static final String DISCOUNT_ID = "id";
    private static final String DISCOUNT_VALUE = "value";
    private static final String DISCOUNT_IS_AVAILABLE = "is_available";
    private static final String DISCOUNT_START_DATE = "start_date";
    private static final String DISCOUNT_FINISH_DATE = "finish_date";

    private static final int AVAILABLE_DISCOUNT = 1;
    private static final int UNAVAILABLE_DISCOUNT = 0;

    private static final String ADD_DISCOUNT_QUERY =
            "INSERT INTO " + DATABASE + "." + DISCOUNT_TABLE + " (" +
                DISCOUNT_VALUE + ", " +
                DISCOUNT_START_DATE + ", " +
                DISCOUNT_FINISH_DATE +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String UPDATE_DISCOUNT_QUERY =
            "UPDATE " + DATABASE + "." + DISCOUNT_TABLE +
            " SET " +
                DISCOUNT_VALUE + "=?, " +
                DISCOUNT_START_DATE + "=?" +
                DISCOUNT_FINISH_DATE + "=?" +
            " WHERE " +
                DISCOUNT_ID + "=?";

    private static final String GET_DISCOUNT_QUERY =
            "SELECT " +
                    DISCOUNT_VALUE + ", " +
                    DISCOUNT_START_DATE +
            " FROM " +
                DATABASE + "." + DISCOUNT_TABLE +
            " WHERE " +
                DISCOUNT_ID + "=? AND" +
                DISCOUNT_IS_AVAILABLE + "=" + AVAILABLE_DISCOUNT;

    private static final String GET_DISCOUNTS_FOR_DATE_QUERY =
            "SELECT " +
                DISCOUNT_ID + ", " +
                DISCOUNT_VALUE + ", " +
                DISCOUNT_START_DATE + ", " +
                DISCOUNT_FINISH_DATE +
            " FROM " +
                DATABASE + "." + DISCOUNT_TABLE +
            " WHERE " +
                DISCOUNT_START_DATE + "<=? AND " +
                DISCOUNT_FINISH_DATE + ">=? AND " +
                DISCOUNT_IS_AVAILABLE + "=" + AVAILABLE_DISCOUNT;

    private static final String SET_DISCOUNT_AVAILABLE =
            "UPDATE " + DATABASE + "." + DISCOUNT_TABLE +
            " SET " +
                DISCOUNT_IS_AVAILABLE + "=?" +
            " WHERE " +
                DISCOUNT_ID + "=?";

    @Override
    public void addDiscount(Discount discount) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();

            connection.setAutoCommit(false);

            statement = connection.prepareStatement(ADD_DISCOUNT_QUERY);

            statement.setByte(1, discount.getValue());
            statement.setDate(2, ArgumentExchanger.exchangeToSQLDate(discount.getStartDate()));
            statement.setDate(3, ArgumentExchanger.exchangeToSQLDate(discount.getFinishDate()));

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during adding new entity");
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public void updateDiscount(int discountId, Discount discount) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_DISCOUNT_QUERY);

            statement.setByte(1, discount.getValue());
            statement.setDate(2, ArgumentExchanger.exchangeToSQLDate(discount.getStartDate()));
            statement.setDate(3, ArgumentExchanger.exchangeToSQLDate(discount.getFinishDate()));
            statement.setInt(4, discount.getId());

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during updating");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public Discount getDiscount(int discountId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_DISCOUNT_QUERY);

            statement.setInt(1, discountId);

            set = statement.executeQuery();

            Discount discount = null;

            while (set.next()) {
                discount = new Discount();

                java.util.Date startDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(DISCOUNT_START_DATE));
                java.util.Date finishDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(DISCOUNT_FINISH_DATE));
                byte value = set.getByte(DISCOUNT_VALUE);

                discount.setId(discountId);
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);
                discount.setValue(value);

            }
            return discount;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public List<Discount> getDiscountForDate(Date date) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Discount> discounts = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_DISCOUNTS_FOR_DATE_QUERY);

            statement.setDate(1, ArgumentExchanger.exchangeToSQLDate(date));

            set = statement.executeQuery();

            discounts = new ArrayList<Discount>();

            while (set.next()) {
                Discount discount = new Discount();

                int categoryId = set.getInt(DISCOUNT_ID);
                java.util.Date startDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(DISCOUNT_START_DATE));
                java.util.Date finishDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(DISCOUNT_FINISH_DATE));
                byte value = set.getByte(DISCOUNT_VALUE);

                discount.setId(categoryId);
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);
                discount.setValue(value);

                discounts.add(discount);
            }
            return discounts;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public void setDiscountAvailable(int discountId, boolean available) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_DISCOUNT_AVAILABLE);
            statement.setBoolean(1, available);
            statement.setInt(2, discountId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during changing state");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            dbConnector.closeConnection(connection);
        }
    }
}

package com.training.web_store.dao.impl.store;

import com.training.web_store.dao.PhotoDAO;
import com.training.web_store.bean.store.Photo;
import com.training.web_store.dao.exception.DAOException;
import com.training.util.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class PhotoDAOImpl implements PhotoDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String PHOTO_TABLE = "thing_image";
    private static final String PHOTO_ID = "id";
    private static final String PHOTO_HREF = "href";
    private static final String PHOTO_REAL_NAME = "real_name";
    private static final String THING_ID = "thing_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String PHOTO_IS_AVAILABLE = "is_available";

    private static final int PHOTO_AVAILABLE = 1;

    private static final String ADD_THING_PHOTO_QUERY =
            "INSERT INTO " + DATABASE + "." + PHOTO_TABLE + " (" +
                PHOTO_REAL_NAME + ", " +
                PHOTO_HREF + ", " +
                THING_ID +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String ADD_PRODUCT_PHOTO_QUERY =
            "INSERT INTO " + DATABASE + "." + PHOTO_TABLE + " (" +
                PHOTO_REAL_NAME + ", " +
                PHOTO_HREF + ", " +
                PRODUCT_ID +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String GET_PHOTOS_QUERY =
            "SELECT " +
                PHOTO_ID + ", " +
                PHOTO_HREF + ", " +
                PHOTO_REAL_NAME +
            " FROM " +
                DATABASE + "." + PHOTO_TABLE +
            " WHERE " +
                THING_ID + "=? AND" +
                PHOTO_IS_AVAILABLE + "=" + PHOTO_AVAILABLE;

    private static final String SET_PHOTO_AVAILABLE =
            "UPDATE " + DATABASE + "." + PHOTO_TABLE +
            " SET " +
                PHOTO_IS_AVAILABLE + "=?" +
            " WHERE " +
                PHOTO_ID + "=?";

    @Override
    public void addThingPhoto(int thingId, String realName, String href) throws DAOException {
        addPhoto(ADD_THING_PHOTO_QUERY, thingId, realName, href);
    }

    @Override
    public void addProductPhoto(int productId, String realName, String href) throws DAOException {
        addPhoto(ADD_PRODUCT_PHOTO_QUERY, productId, realName, href);
    }

    private void addPhoto(String query, int id, String realName, String href) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();

            connection.setAutoCommit(false);

            statement = connection.prepareStatement(query);

            statement.setString(1, realName);
            statement.setString(2, href);
            statement.setInt(3, id);

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
    public List<Photo> getPhotos(int thingId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Photo> photos = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_PHOTOS_QUERY);

            statement.setInt(1, thingId);

            set = statement.executeQuery();

            photos = new ArrayList<Photo>();

            while (set.next()) {
                Photo photo = new Photo();

                int photoId = set.getInt(PHOTO_ID);
                String realName = set.getString(PHOTO_REAL_NAME);
                String photoHref = set.getString(PHOTO_HREF);

                photo.setId(photoId);
                photo.setRealName(realName);
                photo.setHref(photoHref);
                photo.setThingId(thingId);

                photos.add(photo);
            }
            return photos;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setAvailable(int photoId, boolean available) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_PHOTO_AVAILABLE);
            statement.setBoolean(1, available);
            statement.setInt(2, photoId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during changing state");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }
}

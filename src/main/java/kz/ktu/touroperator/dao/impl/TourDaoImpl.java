package kz.ktu.touroperator.dao.impl;

import kz.ktu.touroperator.connection.ConnectionPool;
import kz.ktu.touroperator.connection.ConnectionPoolException;
import kz.ktu.touroperator.dao.TourDao;
import kz.ktu.touroperator.model.Tour;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TourDaoImpl implements TourDao {
    private Connection connection;
    private ConnectionPool connectionPool;
    private ResultSet resultSet = null;
    private static final String ADD_QUERY = "insert into tour (name, description, country_id, number_of_days, number_of_people, price, image, date, first_photo, second_photo, third_photo, active) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "update tour set name = ?, description = ?, country_id= ?,number_of_days = ?, number_of_people = ?, price = ?, date = ?  where id= ?";
    private static final String REMOVE_QUERY = "update  tour set active=false where id=?";

    @Override
    public void create(Tour tour) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement newData = connection.prepareStatement(ADD_QUERY)) {
            newData.setString(1, tour.getName());
            newData.setString(2, tour.getDescription());
            newData.setString(3, tour.getCountry());
            newData.setString(4, tour.getNumberOfDays());
            newData.setString(5, tour.getNumberOfPeople());
            newData.setBigDecimal(6, tour.getPrice());
            newData.setDate(7, tour.getDate());

            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTour(Tour tour, Long id) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement newData = connection.prepareStatement(ADD_QUERY)) {
            newData.setString(1, tour.getName());
            newData.setString(2, tour.getDescription());
            newData.setLong(3, id);
            newData.setString(4, tour.getNumberOfDays());
            newData.setString(5, tour.getNumberOfPeople());
            newData.setBigDecimal(6, tour.getPrice());
            newData.setString(7,tour.getImage());
            newData.setDate(8, tour.getDate());
            newData.setString(9,tour.getFirstPhoto());
            newData.setString(10,tour.getSecondPhoto());
            newData.setString(11,tour.getThirdsPhoto());
            newData.setBoolean(12,tour.isActive());

            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tour tour) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement newData = connection.prepareStatement(UPDATE_QUERY)) {
            newData.setString(1, tour.getName());
            newData.setString(2, tour.getDescription());
            newData.setString(3, tour.getCountry());
            newData.setString(4, tour.getNumberOfDays());
            newData.setString(5, tour.getNumberOfPeople());
            newData.setBigDecimal(6, tour.getPrice());
            newData.setDate(7, tour.getDate());
            newData.setLong(8, tour.getId());
            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Tour tour) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement newData = connection.prepareStatement(REMOVE_QUERY);
            newData.setLong(1, tour.getId());
            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

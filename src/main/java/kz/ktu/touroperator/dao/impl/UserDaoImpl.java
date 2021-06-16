package kz.ktu.touroperator.dao.impl;

import kz.ktu.touroperator.connection.ConnectionPool;
import kz.ktu.touroperator.connection.ConnectionPoolException;
import kz.ktu.touroperator.dao.UserDAO;
import kz.ktu.touroperator.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserDaoImpl implements UserDAO {

    private Connection connection;
    private ConnectionPool connectionPool;
    private ResultSet resultSet = null;
    private static final String ADD_QUERY = "insert into users (username, password, first_name, last_name, phone, email, street, house_number,apartment_number, activation_code, active) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "update users set first_name = ?, last_name = ?, phone = ?,email = ?, street = ?, house_number = ?, apartment_number = ?  where id= ?";
    private static final String REMOVE_QUERY = "update  users set active=false where id=?";

    @Override
    public void create(User user) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement newData = connection.prepareStatement(ADD_QUERY)) {
            newData.setString(1, user.getUsername());
            newData.setString(2, user.getPassword());
            newData.setString(3, user.getFirstName());
            newData.setString(4, user.getLastName());
            newData.setString(5, user.getPhone());
            newData.setString(6, user.getEmail());
            newData.setString(7, user.getStreet());
            newData.setString(8, user.getHouseNumber());
            newData.setString(9, user.getApartmentNumber());
            newData.setString(10, user.getActivationCode());
            newData.setBoolean(11, user.isActive());

            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement newData = connection.prepareStatement(UPDATE_QUERY)) {
            newData.setString(1, user.getFirstName());
            newData.setString(2, user.getLastName());
            newData.setString(3, user.getPhone());
            newData.setString(4, user.getEmail());
            newData.setString(5, user.getStreet());
            newData.setString(6, user.getHouseNumber());
            newData.setString(7, user.getApartmentNumber());
            newData.setLong(8, user.getId());
            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(User user) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement newData = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            newData.setString(1, user.getPassword());
            newData.setLong(2, user.getId());
            newData.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void delete(User user) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement newData = connection.prepareStatement(REMOVE_QUERY);
            newData.setLong(1, user.getId());
            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activateUser(User user) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement newData = connection.prepareStatement("UPDATE users SET active = ?, activation_code = ? WHERE id = ?");
            newData.setBoolean(1, user.isActive());
            newData.setString(2, user.getActivationCode());
            newData.setLong(3, user.getId());
            newData.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByUsername(String username) throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        User user = null;
        try (PreparedStatement newData = connection.prepareStatement("SELECT first_name, last_name, email, phone, street, apartment_number, house_number FROM users WHERE username = ?")) {
            newData.setString(1, username);
            resultSet = newData.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setStreet(resultSet.getString("street"));
                user.setApartmentNumber(resultSet.getString("apartment_number"));
                user.setHouseNumber(resultSet.getString("house_number"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

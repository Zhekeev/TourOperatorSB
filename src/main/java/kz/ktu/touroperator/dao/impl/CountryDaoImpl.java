package kz.ktu.touroperator.dao.impl;

import kz.ktu.touroperator.connection.ConnectionPool;
import kz.ktu.touroperator.connection.ConnectionPoolException;
import kz.ktu.touroperator.dao.CountryDao;
import kz.ktu.touroperator.model.Country;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

@Service
public class CountryDaoImpl implements CountryDao {
    private Connection connection;
    private ConnectionPool connectionPool;
    private ResultSet resultSet = null;
    private static final String ADD_QUERY = "insert into country (name, description) values (?,?)";
    private static final String UPDATE_QUERY = "update country set name = ?, description = ? where id= ?";
    private static final String REMOVE_QUERY = "delete country where id = ?";

    @Override
    public void create(Country country) throws SQLException, ConnectionPoolException, ParseException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement newData = connection.prepareStatement(ADD_QUERY)) {
            newData.setString(1, country.getName());
            newData.setString(2, country.getDescription());

            newData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Country object) throws SQLException, ConnectionPoolException {

    }

    @Override
    public void delete(Country object) throws SQLException, ConnectionPoolException {

    }
}

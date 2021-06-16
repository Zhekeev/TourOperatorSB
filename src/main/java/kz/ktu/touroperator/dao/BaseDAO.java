package kz.ktu.touroperator.dao;

import kz.ktu.touroperator.connection.ConnectionPoolException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface BaseDAO<T> {
    void create(T object) throws SQLException, ConnectionPoolException, ParseException;

    void update(T object) throws SQLException, ConnectionPoolException;

    void delete(T object) throws SQLException, ConnectionPoolException;

}

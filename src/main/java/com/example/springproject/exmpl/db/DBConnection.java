package com.example.springproject.exmpl.db;

import com.example.springproject.exmpl.constants.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnection {
    INSTANCE;
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:postgresql://" + DBConstants.DB_HOST + ":" + DBConstants.PORT + "/" +
                    DBConstants.DB, DBConstants.USER, DBConstants.PASSWORD);
        }
        return connection;
    }
}

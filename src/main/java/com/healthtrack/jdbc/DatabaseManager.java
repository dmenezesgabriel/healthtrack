package com.healthtrack.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.healthtrack.util.Resources;

public class DatabaseManager {
    public static Connection getConnection() {
        Properties props = Resources.readProperties("/db.properties");

        // Get each property value.
        String dbDriverClass = props.getProperty("postgres.jdbc.driverClassName");
        String dbConnUrl = props.getProperty("postgres.jdbc.url");
        String dbUserName = props.getProperty("postgres.jdbc.username");
        String dbPassword = props.getProperty("postgres.jdbc.password");

        Connection connection = null;
        try {
            Class.forName(dbDriverClass);
            connection = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return connection;
    }
}
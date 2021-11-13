package com.healthtrack.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseManager {
    private static File dbSettingsPropertyFile = new File(
            DatabaseManager.class.getResource("/db.properties").getFile());

    public static Connection getConnection() {
        Properties props = null;
        try {
            FileReader fReader = new FileReader(dbSettingsPropertyFile);
            props = new Properties();
            props.load(fReader);
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }

        // Get each property value.
        String dbDriverClass = props.getProperty("oracle.jdbc.driverClassName");
        String dbConnUrl = props.getProperty("oracle.jdbc.url");
        String dbUserName = props.getProperty("oracle.jdbc.username");
        String dbPassword = props.getProperty("oracle.jdbc.password");

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
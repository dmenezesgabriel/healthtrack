package com.healthtrack.jdbc;

import java.sql.Connection;

public class ConnectionManager {
    private static ConnectionManager connectionManager;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        // Singleton
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() {
        return DatabaseManager.getConnection();
    }
}
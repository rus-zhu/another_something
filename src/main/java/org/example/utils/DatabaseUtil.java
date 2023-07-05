package org.example.utils;

import org.example.config.DotenvConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    public static final String URL = DotenvConfig.Dotenv().get("URL");
    public static final String USERNAME = DotenvConfig.Dotenv().get("USERNAME");
    public static final String PASSWORD = DotenvConfig.Dotenv().get("PASSWORD");
    public static final String DRIVER = DotenvConfig.Dotenv().get("DRIVER");

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection Connection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

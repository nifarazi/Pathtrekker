package com.example.pathtrekker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProfileUserJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "nanjiba@282002";
    private static String currentUsername;

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static ResultSet getUserData(String username) throws SQLException {
        String query = "SELECT username, email, full_name FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        return preparedStatement.executeQuery();
    }
}
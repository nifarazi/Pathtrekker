package com.example.pathtrekker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadProfileUserJDBC {
    private static String currentUsername;

    // Set the logged-in username
    public static void setCurrentUsername(String username) {
        if (username != null && !username.isEmpty()) {
            currentUsername = username;
            System.out.println("Current username set: " + currentUsername);
        }
    }

    // Get the logged-in username
    public static String getCurrentUsername() {
        if (currentUsername == null || currentUsername.isEmpty()) {
            System.out.println("Warning: No username is currently set.");
            return null;
        }
        return currentUsername;
    }

    // Fetch user details
    public static ResultSet getUserData() throws SQLException {
        if (currentUsername == null) {
            System.out.println("No user is logged in.");
            return null;
        }

        String query = "SELECT username, email, first_name, last_name FROM user WHERE username = ?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, currentUsername);
        return preparedStatement.executeQuery();
    }
}

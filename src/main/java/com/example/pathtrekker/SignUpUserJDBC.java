package com.example.pathtrekker;

import java.sql.*;

public class SignUpUserJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "nanjiba@282002";

    public static void main(String[] args) { }

    public static boolean usernameExists(String username) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static void signIn(String firstName, String lastName, String username, String email, String password) {
        String insertQuery = "INSERT INTO user (first_name, last_name, username, email, password) VALUES (?, ?, ?, ?, ?)";



        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, email);
            pstmt.setString(5, password);


            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User signed in successfully!");
            }

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}

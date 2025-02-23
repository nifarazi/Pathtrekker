package com.example.pathtrekker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SignInUserJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mirpurdohs832";

    public static void main(String[] args) {
    }

    public static boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProfileUserJDBC.setCurrentUsername(username);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.example.pathtrekker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mirpurdohs832";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

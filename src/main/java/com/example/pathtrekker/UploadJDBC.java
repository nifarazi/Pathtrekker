package com.example.pathtrekker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadJDBC {

    public static boolean uploadPhoto(String username, InputStream imageStream, String caption) {
        if (username == null || username.isEmpty()) {
            System.out.println("Error: Username is null, cannot upload photo.");
            return false;
        }

        String sql = "INSERT INTO photos (username, image, caption) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("Database connection established."); // Debugging

            stmt.setString(1, username);
            stmt.setBinaryStream(2, imageStream);
            stmt.setString(3, caption);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Debugging
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

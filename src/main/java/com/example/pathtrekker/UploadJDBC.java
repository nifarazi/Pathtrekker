package com.example.pathtrekker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadJDBC {

    public static boolean uploadPhoto(String username, InputStream imageStream, String caption) {
        if (username == null || username.isEmpty()) {
            System.out.println("Error: Username is null or empty, cannot upload photo.");
            return false;
        }

        String sql = "INSERT INTO photos (username, image, caption) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("✅ Database connection established.");

            stmt.setString(1, username);
            stmt.setBlob(2, imageStream);  // ✅ Using setBlob() for large images
            stmt.setString(3, caption);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("✅ Photo uploaded successfully! Rows affected: " + rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("❌ ERROR: Photo upload failed!");
            e.printStackTrace();
            return false;
        }
    }
}

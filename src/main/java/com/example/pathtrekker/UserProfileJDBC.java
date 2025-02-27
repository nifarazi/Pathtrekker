package com.example.pathtrekker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProfileJDBC {

    // Fetch all photos uploaded by the logged-in user
    public static List<Photo> getUserPhotos(String username) {
        List<Photo> photos = new ArrayList<>();
        String query = """
                SELECT p.id, p.image, p.caption, 
                       (SELECT COUNT(*) FROM likes WHERE photo_id = p.id) AS like_count
                FROM photos p WHERE p.username = ? ORDER BY p.uploaded_at DESC;
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                byte[] image = rs.getBytes("image");
                String caption = rs.getString("caption");
                int likeCount = rs.getInt("like_count");

                Photo photo = new Photo(id, username, image, caption, likeCount);
                photo.setComments(getComments(id)); // Fetch comments
                photos.add(photo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return photos;
    }

    // Fetch comments for a specific photo
    public static List<Comment> getComments(int photoId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT username, comment FROM comments WHERE photo_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, photoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comments.add(new Comment(rs.getString("username"), rs.getString("comment")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
}

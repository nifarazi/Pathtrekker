package com.example.pathtrekker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MomentsJDBC {

    // Fetch all photos with like counts and comments
    public static List<Photo> getPhotos() {
        List<Photo> photos = new ArrayList<>();

        String query = """
                SELECT p.id, p.username, p.image, p.caption, 
                       (SELECT COUNT(*) FROM likes l WHERE l.photo_id = p.id) AS like_count
                FROM photos p ORDER BY p.uploaded_at DESC;
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                byte[] image = rs.getBytes("image");
                String caption = rs.getString("caption");
                int likeCount = rs.getInt("like_count");

                Photo photo = new Photo(id, username, image, caption, likeCount);
                photo.setComments(getComments(id)); // Get comments for each photo
                photos.add(photo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return photos;
    }

    // Fetch comments for a given photo
    public static List<Comment> getComments(int photoId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT username, comment FROM comments WHERE photo_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, photoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String commentText = rs.getString("comment");
                comments.add(new Comment(username, commentText));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    // Add a like to a photo
    public static void likePhoto(String username, int photoId) {
        String query = "INSERT INTO likes (username, photo_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, photoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a comment to a photo
    public static void addComment(String username, int photoId, String comment) {
        String query = "INSERT INTO comments (username, photo_id, comment) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, photoId);
            stmt.setString(3, comment);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

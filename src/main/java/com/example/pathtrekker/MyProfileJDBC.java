package com.example.pathtrekker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyProfileJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "nanjiba@282002";
    private static String currentUsername;

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static User getUserDetails(String username) {
        String query = "SELECT first_name, last_name, email FROM user WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                return new User(username, firstName, lastName, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addBucketListItem(String username, String place) {
        String query = "INSERT INTO bucket_list (username, place, visited) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, place);
            preparedStatement.setBoolean(3, false);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateBucketListItemVisited(String username, String place, boolean visited) {
        String sql = "UPDATE bucket_list SET visited = ? WHERE username = ? AND place = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, visited);
            pstmt.setString(2, username);
            pstmt.setString(3, place);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<BucketListItem> getBucketListItems(String username) {
        List<BucketListItem> bucketList = new ArrayList<>();
        String query = "SELECT id, place, visited FROM bucket_list WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String place = resultSet.getString("place");
                boolean visited = resultSet.getBoolean("visited");
                bucketList.add(new BucketListItem(id, place, visited));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketList;
    }

    public static class User {
        private final String username;
        private final String firstName;
        private final String lastName;
        private final String email;

        public User(String username, String firstName, String lastName, String email) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }
    }

    public static class BucketListItem {
        private final int id;
        private final String place;
        private boolean visited;

        public BucketListItem(int id, String place, boolean visited) {
            this.id = id;
            this.place = place;
            this.visited = visited;
        }

        public int getId() {
            return id;
        }

        public String getPlace() {
            return place;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }
}
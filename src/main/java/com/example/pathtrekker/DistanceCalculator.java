package com.example.pathtrekker;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class DistanceCalculator {
    private static final String API_KEY = "12752a98964c4beda89170492b6cccf6"; // Your OpenCage API Key

    public static double getDistance(String location1, String location2) {
        if (location1.equalsIgnoreCase(location2)) {
            return 0; // Return 0 if both locations are the same
        }

        String sql = "SELECT distance_km FROM distances WHERE (location1 = ? AND location2 = ?) OR (location1 = ? AND location2 = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location1);
            pstmt.setString(2, location2);
            pstmt.setString(3, location2);
            pstmt.setString(4, location1);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Distance retrieved from DB: " + location1 + " to " + location2 + " = " + rs.getDouble("distance_km"));
                return rs.getDouble("distance_km");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If not found, calculate and store
        double distance = calculateDistance(location1, location2);
        if (distance >= 0) {
            storeDistance(location1, location2, distance);
        }
        return distance;
    }

    private static void storeDistance(String location1, String location2, double distance) {
        String sql = "INSERT INTO distances (location1, location2, distance_km) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE distance_km = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location1);
            pstmt.setString(2, location2);
            pstmt.setDouble(3, distance);
            pstmt.setDouble(4, distance);
            pstmt.executeUpdate();
            System.out.println("Distance stored: " + location1 + " to " + location2 + " = " + distance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static double calculateDistance(String location1, String location2) {
        double[] coord1 = getCoordinates(location1);
        double[] coord2 = getCoordinates(location2);

        if (coord1 == null || coord2 == null) {
            System.err.println("Coordinates not found for: " + location1 + " or " + location2);
            return -1;
        }

        final int R = 6371; // Earth's radius in km
        double dLat = Math.toRadians(coord2[0] - coord1[0]);
        double dLon = Math.toRadians(coord2[1] - coord1[1]);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(coord1[0])) * Math.cos(Math.toRadians(coord2[0])) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;
        System.out.println("Calculated distance: " + location1 + " to " + location2 + " = " + distance);
        return distance;
    }

    public static double[] getCoordinates(String location) {
        try {
            String urlString = "https://api.opencagedata.com/geocode/v1/json?q=" +
                    location.replace(" ", "%20") +
                    "&key=" + API_KEY;

            HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
            JSONArray results = jsonObject.getJSONArray("results");

            if (results.length() == 0) return null;

            JSONObject locationData = results.getJSONObject(0).getJSONObject("geometry");
            return new double[]{locationData.getDouble("lat"), locationData.getDouble("lng")};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
package com.example.pathtrekker;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DistanceCalculator {
    private static final String API_KEY = "12752a98964c4beda89170492b6cccf6"; // Replace with your OpenCage API Key

    // Method to fetch latitude & longitude of a location
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

    // Method to calculate distance using the Haversine formula
    public static double calculateDistance(String location1, String location2) {
        double[] coord1 = getCoordinates(location1);
        double[] coord2 = getCoordinates(location2);

        if (coord1 == null || coord2 == null) {
            return -1; // Return -1 if coordinates cannot be retrieved
        }

        final int R = 6371; // Radius of the Earth in km
        double dLat = Math.toRadians(coord2[0] - coord1[0]);
        double dLon = Math.toRadians(coord2[1] - coord1[1]);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(coord1[0])) * Math.cos(Math.toRadians(coord2[0])) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in kilometers
    }
}

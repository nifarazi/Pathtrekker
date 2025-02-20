package com.example.pathtrekker;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelService {

    public List<String> getHotels(String division, String priceRange, int roomsRequired) {
        List<String> hotelList = new ArrayList<>();
        String query = "SELECT name, amenities, nightly_rate, email, phone_number " +
                "FROM hotel WHERE division = ? AND price_range = ? AND available_rooms >= ? " +
                "ORDER BY nightly_rate LIMIT 4";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, division);
            stmt.setString(2, priceRange);
            stmt.setInt(3, roomsRequired);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String hotelInfo = String.format("Name: %s\nAmenities: %s\nRate: BDT %.2f\nEmail: %s\nPhone: %s\n",
                        rs.getString("name"),
                        rs.getString("amenities"),
                        rs.getDouble("nightly_rate"),
                        rs.getString("email"),
                        rs.getString("phone_number"));
                hotelList.add(hotelInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
}

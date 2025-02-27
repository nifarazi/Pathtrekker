package com.example.pathtrekker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalEventsJDBC {

    public static List<LocalEvent> getEventsByDivision(String division) {
        List<LocalEvent> events = new ArrayList<>();
        String query = "SELECT * FROM events WHERE division = ? ORDER BY start_date ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, division);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("event_name");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String openingTime = rs.getString("opening_time");
                String closingTime = rs.getString("closing_time");
                String location = rs.getString("location");
                String description = rs.getString("description");

                events.add(new LocalEvent(id, eventName, startDate, endDate, openingTime, closingTime, location, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}

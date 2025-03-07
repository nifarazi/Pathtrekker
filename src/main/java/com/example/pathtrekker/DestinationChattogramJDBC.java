package com.example.pathtrekker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationChattogramJDBC {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/register";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "nanjiba@282002";

    public static ResultSet getDhakaDestinations(int limit, int offset) throws SQLException {
        String query = "SELECT * FROM destinationsDivisions WHERE division='Chattogram' LIMIT ? OFFSET ?";
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // Create a scrollable and updatable ResultSet
        PreparedStatement preparedStatement = connection.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, // Make the ResultSet scrollable
                ResultSet.CONCUR_READ_ONLY
        );
        preparedStatement.setInt(1, limit);
        preparedStatement.setInt(2, offset);

        // Execute the query and return the ResultSet
        return preparedStatement.executeQuery();
    }
}
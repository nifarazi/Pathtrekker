package com.example.pathtrekker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationRajshahiJDBC {

    public static ResultSet getDhakaDestinations(int limit, int offset) throws SQLException {
        String query = "SELECT * FROM destinationsDivisions WHERE division='Rajshahi' LIMIT ? OFFSET ?";
        Connection connection = DatabaseConnection.getConnection();

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
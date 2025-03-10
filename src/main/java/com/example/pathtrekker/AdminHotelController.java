package com.example.pathtrekker;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;

public class AdminHotelController {

    @FXML
    private TextField hotelNameField, emailField, phoneField;
    @FXML
    private ComboBox<String> divisionComboBox, priceRangeComboBox;
    @FXML
    private Button insertButton, deleteButton, backButton;

    // Fixed values for hotel categories
    private static final int LOW_ROOMS = 50;
    private static final int MID_ROOMS = 30;
    private static final int HIGH_ROOMS = 15;

    private static final String LOW_AMENITIES = "Basic Wifi, Parking, Breakfast";
    private static final String MID_AMENITIES = "Wifi, Pool, Gym, Parking, Breakfast";
    private static final String HIGH_AMENITIES = "Luxury Rooms, Spa, Pool, Gym, Parking, Breakfast";

    private static final double LOW_RATE = 2000.00;
    private static final double MID_RATE = 5000.00;
    private static final double HIGH_RATE = 10000.00;

    @FXML
    public void initialize() {
        insertButton.setCursor(Cursor.HAND);
        deleteButton.setCursor(Cursor.HAND);
        backButton.setCursor(Cursor.HAND);
        // Populate ComboBox with values
        divisionComboBox.setItems(FXCollections.observableArrayList(
                "Dhaka", "Chattogram", "Khulna", "Barishal", "Sylhet", "Rangpur", "Rajshahi", "Mymensingh"
        ));

        priceRangeComboBox.setItems(FXCollections.observableArrayList("low", "mid", "high"));
    }

    @FXML
    private void insertHotel() {
        String name = hotelNameField.getText().trim();
        String division = divisionComboBox.getValue();
        String priceRange = priceRangeComboBox.getValue();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || division == null || priceRange == null || email.isEmpty() || phone.isEmpty()) {
            showAlert("Input Error", "All fields are required.");
            return;
        }

        int rooms;
        String amenities;
        double rate;

        switch (priceRange) {
            case "low":
                rooms = LOW_ROOMS;
                amenities = LOW_AMENITIES;
                rate = LOW_RATE;
                break;
            case "mid":
                rooms = MID_ROOMS;
                amenities = MID_AMENITIES;
                rate = MID_RATE;
                break;
            case "high":
                rooms = HIGH_ROOMS;
                amenities = HIGH_AMENITIES;
                rate = HIGH_RATE;
                break;
            default:
                showAlert("Error", "Invalid price range.");
                return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT id FROM hotel WHERE name = ? AND division = ?");
             PreparedStatement insertStmt = conn.prepareStatement(
                     "INSERT INTO hotel (name, division, price_range, available_rooms, amenities, nightly_rate, email, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            checkStmt.setString(1, name);
            checkStmt.setString(2, division);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                showAlert("Error", "This hotel already exists.");
                return;
            }

            insertStmt.setString(1, name);
            insertStmt.setString(2, division);
            insertStmt.setString(3, priceRange);
            insertStmt.setInt(4, rooms);
            insertStmt.setString(5, amenities);
            insertStmt.setDouble(6, rate);
            insertStmt.setString(7, email);
            insertStmt.setString(8, phone);
            insertStmt.executeUpdate();

            showAlert("Success", "Hotel added successfully.");
            clearFields();

        } catch (SQLException e) {
            showAlert("Database Error", "Failed to insert hotel.");
        }
    }

    @FXML
    private void deleteHotel() {
        String name = hotelNameField.getText().trim();
        String division = divisionComboBox.getValue();

        if (name.isEmpty() || division == null) {
            showAlert("Input Error", "Hotel Name and Division are required.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM hotel WHERE name = ? AND division = ?")) {

            stmt.setString(1, name);
            stmt.setString(2, division);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Success", "Hotel deleted successfully.");
                clearFields();
            } else {
                showAlert("Error", "Hotel not found.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete hotel.");
        }
    }

    @FXML
    private void handleBackButton() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            ChangeScene sceneChanger = new ChangeScene();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminDashboard.fxml"); // Navigate back to Dashboard
        } catch (IOException e) {
            showAlert("Error", "Failed to return to the Admin Dashboard.\n" + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        hotelNameField.clear();
        divisionComboBox.getSelectionModel().clearSelection();
        priceRangeComboBox.getSelectionModel().clearSelection();
        emailField.clear();
        phoneField.clear();
    }
}
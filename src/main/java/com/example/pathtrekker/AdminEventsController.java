package com.example.pathtrekker;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

public class AdminEventsController {

    @FXML
    private ComboBox<String> divisionComboBox;
    @FXML
    private TextField eventNameField, locationField, openingTimeField, closingTimeField;
    @FXML
    private DatePicker startDatePicker, endDatePicker;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button insertButton, deleteButton, backButton;

    @FXML
    public void initialize() {
        // Populate Division ComboBox
        divisionComboBox.setItems(FXCollections.observableArrayList(
                "Dhaka", "Chattogram", "Khulna", "Barishal", "Sylhet", "Rangpur", "Rajshahi", "Mymensingh"
        ));
    }

    private Connection connectDB() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "mirpurdohs832");
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to connect to the database.\n" + e.getMessage());
            return null;
        }
    }

    @FXML
    private void insertEvent() {
        String division = divisionComboBox.getValue();
        String eventName = eventNameField.getText().trim();
        String location = locationField.getText().trim();
        String startDate = (startDatePicker.getValue() != null) ? startDatePicker.getValue().toString() : "";
        String endDate = (endDatePicker.getValue() != null) ? endDatePicker.getValue().toString() : "";
        String openingTime = openingTimeField.getText().trim();
        String closingTime = closingTimeField.getText().trim();
        String description = descriptionField.getText().trim();

        // Validate Inputs
        if (division == null || eventName.isEmpty() || location.isEmpty() || startDate.isEmpty() || endDate.isEmpty()
                || openingTime.isEmpty() || closingTime.isEmpty() || description.isEmpty()) {
            showAlert("Input Error", "All fields are required.");
            return;
        }

        Connection conn = connectDB();
        if (conn == null) return; // Prevent NullPointerException if connection fails

        try {
            // Check if event already exists
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT id FROM events WHERE division = ? AND event_name = ?");
            checkStmt.setString(1, division);
            checkStmt.setString(2, eventName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                showAlert("Error", "This event already exists.");
                return;
            }

            // Insert event
            PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO events (division, event_name, start_date, end_date, opening_time, closing_time, location, description) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertStmt.setString(1, division);
            insertStmt.setString(2, eventName);
            insertStmt.setString(3, startDate);
            insertStmt.setString(4, endDate);
            insertStmt.setString(5, openingTime);
            insertStmt.setString(6, closingTime);
            insertStmt.setString(7, location);
            insertStmt.setString(8, description);
            insertStmt.executeUpdate();

            showAlert("Success", "Event added successfully.");
            clearFields();

        } catch (SQLException e) {
            showAlert("Database Error", "Failed to insert event.\n" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                showAlert("Database Error", "Failed to close connection.\n" + e.getMessage());
            }
        }
    }

    @FXML
    private void deleteEvent() {
        String division = divisionComboBox.getValue();
        String eventName = eventNameField.getText().trim();

        // Validate Input
        if (division == null || eventName.isEmpty()) {
            showAlert("Input Error", "Event Name and Division are required.");
            return;
        }

        Connection conn = connectDB();
        if (conn == null) return; // Prevent NullPointerException if connection fails

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM events WHERE division = ? AND event_name = ?");
            stmt.setString(1, division);
            stmt.setString(2, eventName);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Success", "Event deleted successfully.");
                clearFields();
            } else {
                showAlert("Error", "Event not found.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete event.\n" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                showAlert("Database Error", "Failed to close connection.\n" + e.getMessage());
            }
        }
    }

    @FXML
    private void handleBackButton() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            ChangeScene sceneChanger = new ChangeScene();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminDashboard.fxml");
        } catch (IOException e) {
            showAlert("Error", "Failed to return to the Admin Dashboard.\n" + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        divisionComboBox.getSelectionModel().clearSelection();
        eventNameField.clear();
        locationField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        openingTimeField.clear();
        closingTimeField.clear();
        descriptionField.clear();
    }
}

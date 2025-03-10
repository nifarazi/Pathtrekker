package com.example.pathtrekker;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
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
        // Apply styles and hover effects to buttons
        configureButtonStyles(insertButton);
        configureButtonStyles(deleteButton);
        configureButtonStyles(backButton);

        // Set cursor to hand pointer for interactive elements
        insertButton.setCursor(Cursor.HAND);
        deleteButton.setCursor(Cursor.HAND);
        backButton.setCursor(Cursor.HAND);
        startDatePicker.setCursor(Cursor.HAND);
        endDatePicker.setCursor(Cursor.HAND);
        divisionComboBox.setCursor(Cursor.HAND);

        // Populate Division ComboBox
        divisionComboBox.setItems(FXCollections.observableArrayList(
                "Dhaka", "Chattogram", "Khulna", "Barishal", "Sylhet", "Rangpur", "Rajshahi", "Mymensingh"
        ));
    }

    @FXML
    private void insertEvent() throws SQLException {
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

        Connection conn = DatabaseConnection.getConnection();
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
    private void deleteEvent() throws SQLException {
        String division = divisionComboBox.getValue();
        String eventName = eventNameField.getText().trim();

        // Validate Input
        if (division == null || eventName.isEmpty()) {
            showAlert("Input Error", "Event Name and Division are required.");
            return;
        }

        Connection conn = DatabaseConnection.getConnection();
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

    private void configureButtonStyles(Button button) {
        // Default style
        button.setStyle("-fx-background-color: #18392b; -fx-text-fill: white;");

        // Hover effect
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #12291f; -fx-text-fill: white;"));

        // Mouse exit effect (revert to default style)
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #18392b; -fx-text-fill: white;"));

        // Button pressed effect (darker)
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #0d1e16; -fx-text-fill: white;"));

        // Button released effect (revert to hover style)
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #12291f; -fx-text-fill: white;"));
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
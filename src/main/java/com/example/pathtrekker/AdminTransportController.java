package com.example.pathtrekker;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

public class AdminTransportController {

    @FXML
    private ComboBox<String> fromDivisionComboBox, toDivisionComboBox;
    @FXML
    private TextField transportTypeField, fareField, serviceNameField, emailField, phoneField, serviceLocationField;
    @FXML
    private Button insertButton, deleteButton, backButton;

    private static final String PREDEFINED_DEPARTURE = "2025-01-01";
    private static final String PREDEFINED_RETURN = "2025-01-10";

    @FXML
    public void initialize() {
        insertButton.setCursor(Cursor.HAND);
        deleteButton.setCursor(Cursor.HAND);
        backButton.setCursor(Cursor.HAND);
        fromDivisionComboBox.setItems(FXCollections.observableArrayList(
                "Dhaka", "Chattogram", "Khulna", "Barishal", "Sylhet", "Rangpur", "Rajshahi", "Mymensingh"
        ));

        toDivisionComboBox.setItems(FXCollections.observableArrayList(
                "Dhaka", "Chattogram", "Khulna", "Barishal", "Sylhet", "Rangpur", "Rajshahi", "Mymensingh"
        ));
    }

    @FXML
    private void insertTransportService() {
        String fromDivision = fromDivisionComboBox.getValue();
        String toDivision = toDivisionComboBox.getValue();
        String transportType = transportTypeField.getText().trim();
        String fareText = fareField.getText().trim();
        String serviceName = serviceNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String serviceLocation = serviceLocationField.getText().trim();

        if (fromDivision == null || toDivision == null || transportType.isEmpty() || fareText.isEmpty()
                || serviceName.isEmpty() || email.isEmpty() || phone.isEmpty() || serviceLocation.isEmpty()) {
            showAlert("Input Error", "All fields are required.");
            return;
        }

        double fare;
        try {
            fare = Double.parseDouble(fareText);
            if (fare <= 0) {
                showAlert("Input Error", "Fare must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid fare price.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(
                     "SELECT id FROM transport_system WHERE from_division = ? AND to_division = ? AND service_name = ?");
             PreparedStatement insertStmt = conn.prepareStatement(
                     "INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            checkStmt.setString(1, fromDivision);
            checkStmt.setString(2, toDivision);
            checkStmt.setString(3, serviceName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                showAlert("Error", "This transport service already exists.");
                return;
            }

            insertStmt.setString(1, fromDivision);
            insertStmt.setString(2, toDivision);
            insertStmt.setString(3, transportType);
            insertStmt.setString(4, PREDEFINED_DEPARTURE);
            insertStmt.setString(5, PREDEFINED_RETURN);
            insertStmt.setDouble(6, fare);
            insertStmt.setString(7, serviceName);
            insertStmt.setString(8, email);
            insertStmt.setString(9, phone);
            insertStmt.setString(10, serviceLocation);
            insertStmt.executeUpdate();

            showAlert("Success", "Transport service added successfully.");
            clearFields();

        } catch (SQLException e) {
            showAlert("Database Error", "Failed to insert transport service.");
        }
    }

    @FXML
    private void deleteTransportService() {
        String fromDivision = fromDivisionComboBox.getValue();
        String toDivision = toDivisionComboBox.getValue();
        String serviceName = serviceNameField.getText().trim();

        if (fromDivision == null || toDivision == null || serviceName.isEmpty()) {
            showAlert("Input Error", "Service Name, From, and To fields are required.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM transport_system WHERE from_division = ? AND to_division = ? AND service_name = ?")) {

            stmt.setString(1, fromDivision);
            stmt.setString(2, toDivision);
            stmt.setString(3, serviceName);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Success", "Transport service deleted successfully.");
                clearFields();
            } else {
                showAlert("Error", "Transport service not found.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete transport service.");
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
        fromDivisionComboBox.getSelectionModel().clearSelection();
        toDivisionComboBox.getSelectionModel().clearSelection();
        transportTypeField.clear();
        fareField.clear();
        serviceNameField.clear();
        emailField.clear();
        phoneField.clear();
        serviceLocationField.clear();
    }
}
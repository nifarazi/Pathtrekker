package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button reviewRatingsButton, hotelsButton, transportationButton, localAttractionsButton;

    private final ChangeScene sceneChanger = new ChangeScene(); // Using ChangeScene class

    @FXML
    public void initialize() {
        setButtonStyle(reviewRatingsButton);
        setButtonStyle(hotelsButton);
        setButtonStyle(transportationButton);
        setButtonStyle(localAttractionsButton);
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #1C3D24; -fx-text-fill: #CDE8D4; -fx-font-size: 14px; "
                + "-fx-padding: 10px; -fx-border-radius: 5px; -fx-font-weight: bold;");
    }

    @FXML
    private void handleHotelsButton() {
        try {
            Stage stage = (Stage) hotelsButton.getScene().getWindow();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminHotel.fxml");
        } catch (IOException e) {
            showAlert("Error", "Failed to load the Hotel Management page.\n" + e.getMessage());
        }
    }


    @FXML
    private void handleReviewRatingsButton() {
        showAlert("Review and Ratings", "Opening Review and Ratings Page...");
    }

    @FXML
    private void handleTransportationButton() {
        try {
            Stage stage = (Stage) transportationButton.getScene().getWindow();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminTransport.fxml"); // Navigate to Transport Management
        } catch (IOException e) {
            showAlert("Error", "Failed to load the Transport Management page.\n" + e.getMessage());
        }
    }


    @FXML
    private void handleLocalAttractionsButton() {
        try {
            Stage stage = (Stage) localAttractionsButton.getScene().getWindow();
            sceneChanger.changeScene(stage, "/com/example/pathtrekker/AdminEvents.fxml"); // Navigate to Events Management
        } catch (IOException e) {
            showAlert("Error", "Failed to load the Events Management page.\n" + e.getMessage());
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

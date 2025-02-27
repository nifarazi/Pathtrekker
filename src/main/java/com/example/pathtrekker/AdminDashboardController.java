package com.example.pathtrekker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class AdminDashboardController {

    @FXML
    private Button reviewRatingsButton;

    @FXML
    private Button hotelsButton;

    @FXML
    private Button transportationButton;

    @FXML
    private Button localAttractionsButton;

    @FXML
    public void initialize() {
        // Apply button styles dynamically
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
    private void handleReviewRatingsButton() {
        showAlert("Review and Ratings", "Opening Review and Ratings Page...");
    }

    @FXML
    private void handleHotelsButton() {
        showAlert("Hotels", "Opening Hotels Page...");
    }

    @FXML
    private void handleTransportationButton() {
        showAlert("Transportation", "Opening Transportation Page...");
    }

    @FXML
    private void handleLocalAttractionsButton() {
        showAlert("Local Attractions", "Opening Local Attractions Page...");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
